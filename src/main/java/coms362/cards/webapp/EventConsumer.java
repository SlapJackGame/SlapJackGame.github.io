package coms362.cards.webapp;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.jetty.websocket.common.WebSocketSession;

import coms362.cards.events.inbound.Event;
import coms362.cards.events.inbound.EventUnmarshallers;
import coms362.cards.socket.CardSocket;
import coms362.cards.socket.CardSocketListener;
import coms362.cards.socket.SocketMessage;
import coms362.cards.streams.InBoundQueue;
import coms362.cards.streams.RemoteTableGateway;
import coms362.cards.utilities.QueryParams;

public class EventConsumer implements CardSocketListener {
    private InBoundQueue q; 
    private CardSocket cardSocket;
    private EventUnmarshallers handlers;

    public EventConsumer(InBoundQueue q, EventUnmarshallers handlers) {
    	System.out.println("Creating EventConsumer "+this);
    	this.q = q;
    	this.handlers = handlers;
    }

    public void onConnect(WebSocketSession session) {
        System.out.println("onConnect");
        System.out.println("QueryMap="+session.getRequestURI().getQuery());
        QueryParams params = new QueryParams(session.getRequestURI().getQuery());
        params.put("event","ConnectEvent");
        onReceive(new SocketMessage(params.toMap(), cardSocket.hashCode()));
    }

    public void onReceive(SocketMessage event) {
    	Event e = createEvent(event);
    	if (e != null){
    		q.add(e);
    	}
    	//otherwise, drop it on the floor.     	
    }
    
    private Event createEvent(SocketMessage e){
    	Object eventObj = e.get("event");
        if (eventObj == null) {
        	return null;
        }
        
        String eventName = (String) eventObj;
        System.out.println(eventName);
        
        Class klass = handlers.getHandler(eventName);
        System.out.println("handling event "+e.getName());
        System.out.println("registry instance = "+handlers);
        
        if (klass != null){
        	try {
				Method m = klass.getDeclaredMethod("createEvent", SocketMessage.class);
				return (Event) m.invoke(null, e);
			} catch (NoSuchMethodException | SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
        else {
        	//drop it on the floor
        	System.err.println("Unable to process socket event " + e.toString());
        }
        return null;          
            
    }

    public void setCardSocket(CardSocket cardSocket) {
        this.cardSocket = cardSocket;
        RemoteTableGateway.getInstance().setSocket(cardSocket.getRemote());
        RemoteTableGateway.getInstance().registerSocket(""+cardSocket.hashCode(), cardSocket.getRemote());
    }

}

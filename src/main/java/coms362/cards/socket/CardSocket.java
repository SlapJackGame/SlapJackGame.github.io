package coms362.cards.socket;

import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.common.WebSocketSession;

import coms362.cards.streams.RemoteTableGateway;

@WebSocket
public class CardSocket {
    
    private Session session;
    private RemoteEndpoint remote;
    private CardSocketListener cardSocketListener;

    public CardSocket(CardSocketListener cardSocketListener) {
        this.cardSocketListener = cardSocketListener;
        cardSocketListener.setCardSocket(this);
        System.out.println("Creating CardSocket = "+this);
    }
    
    public RemoteEndpoint getRemote() {
        return remote;
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        this.session = null;
    }

    @OnWebSocketConnect
    public void onConnect(Session session) {
        this.session = session;
        this.remote = session.getRemote();
        // TODO: move this to cardSocketListener.
        System.out.println("Setting remote endpoint");
        RemoteTableGateway.getInstance().setSocket(this.remote);
        RemoteTableGateway.getInstance().registerSocket("" + this.hashCode(), this.remote);
        
        cardSocketListener.onConnect((WebSocketSession) session);
    }

    @OnWebSocketMessage
    public void onText(String message) {
        if (session == null) {
            // no connection, do nothing.
            // this is possible due to async behavior
            return;
        }
        
        SocketMessage event = new SocketMessage(message, this.hashCode());
        cardSocketListener.onReceive(event);
    }
    
}
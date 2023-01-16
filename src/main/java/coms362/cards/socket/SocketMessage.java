package coms362.cards.socket;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jetty.util.ajax.JSON;

import coms362.cards.events.inbound.Parameters;

public class SocketMessage implements Parameters {
    private Map map;
    private int socketId = 0; 

    public SocketMessage(String message, int socketHash) {
    	System.out.println("Creating SocketEvent from json="+message);
    	socketId = socketHash;
        try {
            map = (Map) JSON.parse(message);
        } catch (Exception e) {
            // ignore
        } finally {
            if (map == null) {
                map = new HashMap<String, String>();
            }
        	System.out.println("Creating socketEvent socket= "+socketHash+ ": "+map);

        }
    }
    
    public SocketMessage(Map<String,String> map, int socketId) {
    	this.map = map;
    	this.socketId = socketId;
    }
    
    public int getSocketId() {
    	return socketId;
    }
    
	public String getName() {
    	Object eventObj = map.get("event");
    	return (eventObj == null) ? null: (String ) eventObj;
    }

	@Override
    public String get(String key) {
        Object value = map.get(key);
        if (value != null) {
            return value.toString();
        }
        return null;
    }
}

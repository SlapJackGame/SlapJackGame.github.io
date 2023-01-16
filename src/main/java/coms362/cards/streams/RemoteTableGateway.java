package coms362.cards.streams;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jetty.websocket.api.RemoteEndpoint;



public class RemoteTableGateway {
	RemoteEndpoint remote = null;
	
	Map<String, RemoteEndpoint> epIndex = new HashMap<String, RemoteEndpoint>();

	private static RemoteTableGateway instance = null; 
	
	
	private RemoteTableGateway(){
		
	}
	
	public void send(Marshalls e, String socketId) throws IOException {
		System.out.println("send (marshall) requests "+socketId);
		String msg = e.marshall();
		if (msg != null && !msg.isEmpty()){
			System.out.println("  ... sending to object "+getEndpoint(socketId));
			getEndpoint(socketId).sendString(e.marshall());
		}
	}
	
	public void sendString(String msg, String socketId) throws IOException{
		System.out.println("send (msg) requests "+socketId);
		if (msg != null && !msg.isEmpty()){
			getEndpoint(socketId).sendString(msg);
		}
	}
	
	public void registerSocket(String socketId, RemoteEndpoint remote){
		System.out.println("registering socket id="+socketId + ": "+remote);
		epIndex.put(socketId, remote);
	}
	
	public RemoteEndpoint getEndpoint(String socketId){
		RemoteEndpoint rval = epIndex.get(socketId);
		if (rval == null && remote !=null){
			rval = remote;
		}
		return rval;
	}

	public void setSocket(RemoteEndpoint remote) {
		System.out.println("Gateway setting remote: "+instance.toString());
		this.remote = remote;
	}
	
	public boolean isReady(){
		return remote != null;
	}

	public static synchronized RemoteTableGateway getInstance() {
		if (instance == null){

			instance = new RemoteTableGateway();
			System.out.println("Creating Gateway "+instance.toString());
		}
		return instance;
	}
}

package coms362.cards.fiftytwo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Future;

import org.eclipse.jetty.websocket.api.BatchMode;
import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.WriteCallback;

import coms362.cards.test.control.TestLogger;

public class TestEndpoint 
implements RemoteEndpoint, TestLogger
{
	
	public List<String> log = new LinkedList<String>(); 
	
	public TestEndpoint(){
		
	}
	
	@Override
	public void flush() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BatchMode getBatchMode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InetSocketAddress getInetSocketAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendBytes(ByteBuffer arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendBytes(ByteBuffer arg0, WriteCallback arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Future<Void> sendBytesByFuture(ByteBuffer arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendPartialBytes(ByteBuffer arg0, boolean arg1) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendPartialString(String arg0, boolean arg1) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendPing(ByteBuffer arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendPong(ByteBuffer arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendString(String msg) throws IOException {
		log.add(msg);		
	}

	@Override
	public void sendString(String arg0, WriteCallback arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Future<Void> sendStringByFuture(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBatchMode(BatchMode arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		log = new LinkedList<String>();
		
	}

	@Override
	public String getLog() {
		String rval = "";
		Iterator<String> iter = log.iterator();
		while (iter.hasNext()){
			rval+= iter.next()+"\n";
		}
		return rval;
	}

	@Override
	public void log(String msg) {
		log.add(msg);
		
	}

}

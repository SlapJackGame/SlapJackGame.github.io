package coms362.cards.fiftytwo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import coms362.cards.abstractcomp.View;
import coms362.cards.streams.Marshalls;
import coms362.cards.test.control.TestLogger;

public class LoggingView 
implements View, TestLogger 
{

	public List<String> log 
		= new ArrayList<String>();
	
	public void send(Marshalls event) 
			throws IOException 
	{
		log.add(event.stringify());	
	}

	public void clear() {
		log = new ArrayList<String>();		
	}

	public String getLog() {
		String rval = "";
		for (String msg: log) {
			rval += msg +"\n";
		}
		return rval;
	}

	public void log(String msg) {
		log.add(msg);		
	}
	
	@Override
	public int getCameraPosition() {
		return 1;
	}

}

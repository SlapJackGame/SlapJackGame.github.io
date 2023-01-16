package coms362.cards.events.remote;

import coms362.cards.streams.Marshalls;

public class HideButtonRemote implements Marshalls {

	private String remoteId;
	
	public HideButtonRemote(String remoteId){
		this.remoteId = remoteId;
	}
	
	@Override
	public String marshall() {
		return String.format("cards362.getById('%s').hide();\n", remoteId);			 
	}

	@Override
	public String stringify() {
		return String.format("HideButtonRemote");
	}

}

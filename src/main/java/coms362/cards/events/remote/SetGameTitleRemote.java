package coms362.cards.events.remote;

import coms362.cards.streams.Marshalls;

public class SetGameTitleRemote implements Marshalls {
	
	private String title = "";
	
	public SetGameTitleRemote(String newTitle) {
		title = newTitle;
	}

	public String marshall() {
		return String.format("$('#game-title').text('%s');", title);
	}

	public String stringify() {
		return "SetGameTitle = "+title;
	}

}

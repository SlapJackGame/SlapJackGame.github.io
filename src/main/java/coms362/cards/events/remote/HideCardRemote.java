package coms362.cards.events.remote;

import coms362.cards.model.Card;
import coms362.cards.streams.Marshalls;

public class HideCardRemote implements Marshalls {
    
	private Card c; 
	
	public HideCardRemote(Card c) {
		this.c = c;
	}
	
	public String marshall() {
        return String.format("cards362.getById(\'%s\').hideCard();", c.getRemoteId());
	}
	
	public String stringify() {
		return "HideCardRemote card = "+ c.getRemoteId();
	}

}

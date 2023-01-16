package coms362.cards.events.remote;

import coms362.cards.model.Card;
import coms362.cards.streams.Marshalls;

public class CreateCardRemote implements Marshalls {
    
	Card c;

	public CreateCardRemote(Card c) {
		this.c = c;
	}

	public String marshall() {
        return String.format(
                "cards362.createCard(\'%s',\'%s\',\'%s\');\n",
                c.getRemoteId(), c.getSuit(), c.getRank()
        );
	}

	public String stringify() {
		return "CreateRemote Card id="+c.getRemoteId();
	}

}

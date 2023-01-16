package coms362.cards.events.remote;

import coms362.cards.model.Card;
import coms362.cards.model.Pile;
import coms362.cards.streams.Marshalls;

public class RemoveFromPileRemote implements Marshalls {

    private Pile p;
	private Card c;

	public RemoveFromPileRemote(Pile p, Card c) {
	    this.p = p;
		this.c = c;
	}

	public String marshall() {
        return String.format(
                "pile = cards362.getById(\'%s\');"
                + "pile.removeCard(cards362.getById('%s'));\n"
                + "pile.render();\n", 
                p.getRemoteId(),
                c.getRemoteId()
            );
	}

	public String stringify() {
		return "RemoveFromPileRemote card = "+c.getRemoteId();
	}

}


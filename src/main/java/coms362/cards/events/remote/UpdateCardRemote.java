package coms362.cards.events.remote;

import coms362.cards.model.Card;
import coms362.cards.streams.Marshalls;

/**
 * Update the remote card to have the same properties (x, y, face up or down, etc.)
 * of a card object.
 */
public class UpdateCardRemote implements Marshalls {
    
	Card c; 
	
	public UpdateCardRemote(Card c) {
		this.c = c;
	}
	
	public String marshall() {
	    return  String.format("card1 = cards362.getById(\'%s\');\n"
	        + "card1.moveTo(%d, %d, 1, null);\n"
	        + "card1.setFaceUp(%b);\n"
	        + "card1.rotate(%d);\n"
	        + "card1.el.click(cardMouseEvent);\n",
	        c.getRemoteId(),
	        c.getX(), c.getY(),
	        c.isFaceUp(),
	        c.getRotate()
	    );
	}
    
    public String stringify() {
    	return "UpdateRemoteCard id="+c.getRemoteId();
    }
	
}

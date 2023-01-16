package coms362.cards.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A pile is a slight generalization of the common concept "deck". 
 * Piles are just collections of cards that share a nominal position
 * in the view.
 * 
 * @author Robert Ward
 */
public class Pile extends PresentationObject {

    public Map<String, Card> cards = new HashMap<String, Card>();
    private boolean faceUp;
    private Location loc = new Location(0,0);
    
    public Pile(String selector, Location loc, boolean faceUp) {
        super(selector);
        this.loc = loc;
        this.faceUp = faceUp;
    }
    
    public Pile(String selector, Location loc) {
        this(selector, loc, false);
    }

    public Location getLocation() {
        return loc;
    }
    
    public void moveTo(Location loc) {
        this.loc = loc;
    }
    
    public boolean getFaceUp() {
        return faceUp;
    }
    
    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }
    
    public void addCard(Card c) {
        cards.put(c.getRemoteId(), c);
    }

    public Card getCard(String id) {
        return cards.get(id);
    }
    
    public Collection<Card> getCards() {
        return cards.values();
    }

}

package coms362.cards.events.remote;

import coms362.cards.model.Location;
import coms362.cards.model.Pile;
import coms362.cards.streams.Marshalls;

/**
 * Update the remote pile to have the same properties (x, y, face up or down, etc.)
 * of a pile object.
 */
public class UpdatePileRemote implements Marshalls {
    
    private Pile p;

    public UpdatePileRemote(Pile p) {
        this.p = p;
    }

    public String marshall() {
        Location loc = p.getLocation();
        
        return String.format(
                "pile = cards362.getById(\'%s\');"
                + "pile.faceUp = %b;\n"
                + "pile.x = %d;\n"
                + "pile.y = %d;\n"
                + "pile.calcPosition();\n"
                + "pile.render();\n", 
                p.getRemoteId(),
                p.getFaceUp(),
                loc.getX(),
                loc.getY()
        );
    }

    public String stringify() {
        return String.format("AddToPileRemote p=%s, c=%d", p.getRemoteId());
    }

}

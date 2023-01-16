package coms362.cards.events.remote;

import coms362.cards.model.Location;
import coms362.cards.model.Pile;
import coms362.cards.streams.Marshalls;

public class CreatePileRemote implements Marshalls {
	
	private Pile p;
	
	public CreatePileRemote(Pile p){
		this.p = p; 
	}
	
	public String marshall() {
        Location loc = p.getLocation();
        
        return String.format(
                "cards362.createPile(\'%s\', %b, %d, %d);",
                p.getRemoteId(),
                p.getFaceUp(),
                loc.getX(),
                loc.getY()
            );
	}

	public String stringify() {
		return "CreatePile p=" + p.getRemoteId();
	}

}

package coms362.cards.app;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Rules;
import coms362.cards.abstractcomp.Table;
import coms362.cards.events.inbound.Event;
import coms362.cards.streams.InBoundQueue;

public class PlayController {

	private InBoundQueue inQ; 
	private Rules rules;
    
	public PlayController (InBoundQueue inQ, 
			Rules rules)
	{
		this.inQ = inQ;
		this.rules = rules; 
	}
	
	public Event play(Table table, 
			Player player, ViewFacade views) {
	    
		Event nextE = null;
		try {
		
			while (
				! table.isMatchOver()
				&& (nextE = inQ.take()) != null
			){
				Move move = rules
					.eval(nextE, table, player);
				move.apply(table);
				move.apply(views);
				if (move.isMatchEnd()){
					System.err.println("Terminating on MatchEnd "+move);
					break;
				}
			}
		} catch (Exception e){
			System.err.println("Play terminated on exception: "+e.getMessage());
			e.printStackTrace();
		}
		return nextE;
	}

}

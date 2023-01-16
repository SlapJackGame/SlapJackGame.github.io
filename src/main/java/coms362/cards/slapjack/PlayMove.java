package coms362.cards.slapjack;


import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Table;
import coms362.cards.app.ViewFacade;
import coms362.cards.events.remote.AddToPileRemote;
import coms362.cards.events.remote.HideCardRemote;
import coms362.cards.events.remote.RemoveFromPileRemote;
import coms362.cards.events.remote.SetGameTitleRemote;
import coms362.cards.events.remote.ShowCardRemote;
import coms362.cards.events.remote.ShowPlayerScore;
import coms362.cards.events.remote.UpdatePileRemote;
import coms362.cards.model.Card;
import coms362.cards.model.Pile;
import coms362.cards.model.Location;

public class PlayMove implements Move {

	private Card c;
	private Player p;
	private Pile fromPile;
	private Pile toPile;
	private Table table;

	public PlayMove(Card c, Player p, Pile fromPile, Pile toPile) {
		this.c = c;
		this.p = p;
		this.fromPile = fromPile;
		this.toPile = toPile;
	}

	@Override
	public void apply(Table table) {
		this.table = table;
		table.setQuorum(null);
		// Adjust score from played card
		if (p.getPlayerNum() == 1) {
			table.removeFromPile(SlapjackRules.player1_pile, c);
		} else if (p.getPlayerNum() == 2) {
			table.removeFromPile(SlapjackRules.player2_pile, c);
		}

		table.addToScore(p, -1); // Points correlate to number of cards in player's pile
		table.addToPile(toPile.selector, c);
		table.getPile(SlapjackRules.center_pile).setFaceUp(true);
		
		// End match
		if(p.getScore() == 0)
		{
			table.setMatchOver(true);
		}
	}
	

	public void apply(ViewFacade view) {
		view.send(new HideCardRemote(c));
		view.send(new RemoveFromPileRemote(fromPile, c));
		view.send(new AddToPileRemote(toPile, c));
		view.send(new ShowCardRemote(c));
		view.send(new ShowPlayerScore(p, null));

		table.getPile(SlapjackRules.center_pile).moveTo(new Location((table.getRandom().nextInt(250)+200),((table.getRandom().nextInt(250)+200))));
		
		view.send(new UpdatePileRemote(toPile));

		if(p.getPlayerNum()==1 && p.getScore() == 0) {
			view.send(new SetGameTitleRemote("Player 2 wins"));
		}
		else if(p.getPlayerNum()==2 && p.getScore() == 0) {
			view.send(new SetGameTitleRemote("Player 1 wins"));
		}
	}



}

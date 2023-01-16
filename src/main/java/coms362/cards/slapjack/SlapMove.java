package coms362.cards.slapjack;

import java.util.Collection;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Table;
import coms362.cards.app.ViewFacade;
import coms362.cards.events.remote.AddToPileRemote;
import coms362.cards.events.remote.HideCardRemote;
import coms362.cards.events.remote.RemoveFromPileRemote;
import coms362.cards.events.remote.ShowCardRemote;
import coms362.cards.events.remote.ShowPlayerScore;
import coms362.cards.events.remote.UpdatePileRemote;
import coms362.cards.model.Card;
import coms362.cards.model.Pile;

public class SlapMove implements Move {

	private Card c;
	private Player p;
	private Pile fromPile;
	private Pile toPile;

	private Card[] cards;

	public SlapMove(Card c, Player p, Pile fromPile, Pile toPile) {
		this.c = c;
		this.p = p;
		this.fromPile = fromPile;
		this.toPile = toPile;
		cards = new Card[fromPile.getCards().size()];
		cards = fromPile.getCards().toArray(cards);
	}

	@Override
	public void apply(Table table) {

		for (int i = 0; i < cards.length; i++) {
			table.removeFromPile(SlapjackRules.center_pile, cards[i]);
			
			// Adjust score for card
			table.addToScore(p, 1); // Points correlate to number of cards in player's pile
			table.addToPile(toPile.selector, cards[i]);
		}

		table.getPile(toPile.selector).setFaceUp(false);
	}

	public void apply(ViewFacade view) {
		for (int i = 0; i < cards.length; i++) {
			view.send(new HideCardRemote(cards[i]));
			view.send(new RemoveFromPileRemote(fromPile, cards[i]));
			view.send(new AddToPileRemote(toPile, cards[i]));
			view.send(new ShowCardRemote(c));
			view.send(new ShowPlayerScore(p, null));
		}
		view.send(new UpdatePileRemote(toPile));
	}

}

package coms362.cards.slapjack;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Table;
import coms362.cards.app.ViewFacade;
import coms362.cards.events.remote.CreateCardRemote;
import coms362.cards.events.remote.HideButtonRemote;
import coms362.cards.events.remote.UpdateCardRemote;
import coms362.cards.fiftytwo.DealButton;
import coms362.cards.fiftytwo.DealCommand;
import coms362.cards.fiftytwo.P52Rules;
import coms362.cards.model.Card;
import coms362.cards.model.Pile;

public class SlapjackDeal implements Move {
	private Table table;

    public SlapjackDeal(Table table, Player player) {
        this.table = table;
    }

    public void apply(Table table) {
        // TODO Auto-generated method stub

    }

    public void apply(ViewFacade views) {

        try {
            String remoteId = views.getRemoteId(DealButton.kSelector);
            views.send(new HideButtonRemote(remoteId));
            Pile p1 = table.getPile(SlapjackRules.player1_pile);
            Pile p2 = table.getPile(SlapjackRules.player2_pile);
            if (p1 == null || p2 == null) {
                return;
            }
            for (Card c : p1.getCards()) {
                String outVal = "";
                views.send(new CreateCardRemote(c));
                views.send(new UpdateCardRemote(c));
                System.out.println(outVal);
            }
            for (Card c2 : p2.getCards()) {
                String outVal2 = "";
                views.send(new CreateCardRemote(c2));
                views.send(new UpdateCardRemote(c2));
                System.out.println(outVal2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

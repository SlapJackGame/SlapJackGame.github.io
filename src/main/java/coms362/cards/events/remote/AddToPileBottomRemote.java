package coms362.cards.events.remote;

import coms362.cards.model.Card;
import coms362.cards.model.Pile;
import coms362.cards.streams.Marshalls;

public class AddToPileBottomRemote implements Marshalls {
    
        private Pile p;
        private Card c;

        public AddToPileBottomRemote(Pile p, Card c) {
            this.p = p;
            this.c = c;
        }

        public String marshall() {
            return String.format(
                    "pile = cards362.getById(\'%s\');"
                    + "pile.addCardBottom(cards362.getById('%s'));\n"
                    + "pile.render();\n", 
                    p.getRemoteId(),
                    c.getRemoteId()
            );
        }

        public String stringify() {
            return String.format("InsertAtPileBottomRemote p=%s, c=%d", p, c.getRemoteId());
        }
}
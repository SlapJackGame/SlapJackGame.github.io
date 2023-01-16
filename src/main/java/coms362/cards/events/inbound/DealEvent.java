package coms362.cards.events.inbound;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.RulesDispatch;
import coms362.cards.abstractcomp.Table;
import coms362.cards.socket.SocketMessage;

public class DealEvent implements Event, EventFactory {

    public static final String kId = "dealevent";

    public static Event createEvent(SocketMessage sktEvent) {
        return new DealEvent();
    }

    @Override
    public Move dispatch(RulesDispatch rules, Table table, Player player) {
        return rules.apply(this, table, player);
    }

}

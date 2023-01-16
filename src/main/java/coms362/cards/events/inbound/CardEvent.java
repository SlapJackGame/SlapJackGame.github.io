package coms362.cards.events.inbound;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.RulesDispatch;
import coms362.cards.abstractcomp.Table;
import coms362.cards.socket.SocketMessage;

/**
 * Indicates a player has clicked on (i.e., selected) a particular card.
 * 
 * @author Robert Ward
 */
public class CardEvent implements Event, EventFactory {

    public static final String kId = "cardevent";

    public static Event createEvent(SocketMessage sktMsg) {
        return new CardEvent(sktMsg.get("id").toString(), "" + sktMsg.getSocketId());
    }

    private String id;
    private String socketId;

    public CardEvent(String cardId, String socketId) {
        this.id = cardId;
        this.socketId = socketId;
    }

    public String getId() {
        return id;
    }

    @Override
    public Move dispatch(RulesDispatch rules, Table table, Player player) {
        Player source = table.lookupPlayer(this.socketId);
        return rules.apply(this, table, source);
    }

}

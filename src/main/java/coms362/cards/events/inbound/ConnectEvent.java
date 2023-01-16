package coms362.cards.events.inbound;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.RulesDispatch;
import coms362.cards.abstractcomp.Table;
import coms362.cards.app.GameController;
import coms362.cards.fiftytwo.PartyRole;
import coms362.cards.model.PregameSetup;
import coms362.cards.model.Quorum;
import coms362.cards.socket.SocketMessage;

/**
 * An external (inbound) event. This event is Synthesized by the receiving webSocket when a new
 * connection is detected.
 * 
 * @author Robert Ward
 */
public class ConnectEvent implements SysEvent, Event {

    public static final String kId = "ConnectEvent";

    private Parameters params;
    private String socket = "";
    private Quorum quorum = null;
    private PartyRole role = PartyRole.unknown;
    private Integer position = -1;

    public static Event createEvent(SocketMessage sktMsg) {
        return new ConnectEvent(sktMsg);
    }

    public ConnectEvent(SocketMessage e) {
        params = e;
        this.socket = "" + e.getSocketId();
        System.out.println("Constructing Connect event for socket " + socket);
        quorum = new Quorum(e.get("min"), e.get("max"));
        String pos;
        if ((pos = e.get("player")) != null) {
            role = PartyRole.player;
            position = Integer.valueOf(pos);
        }
    }

    @Override
    public Move dispatch(RulesDispatch rules, Table table, Player player) {
        return rules.apply(this, table, player);
    }

    @Override
    public void accept(GameController handler, PregameSetup game) {
        handler.apply(this, game);
    }

    public String getParam(String key) {
        return params.get(key);
    }

    public String getSocketId() {
        return socket;
    }

    public PartyRole getRole() {
        return role;
    }

    public String toString() {
        return String.format("ConnectEvent: %s", socket);
    }

    public Integer getPosition() {
        return position;
    }

    public Quorum getQuorum() {
        return quorum;
    }

}

package coms362.cards.app;

import coms362.cards.abstractcomp.GameFactory;
import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Rules;
import coms362.cards.abstractcomp.Table;
import coms362.cards.abstractcomp.ViewFactory;
import coms362.cards.events.inbound.Event;
import coms362.cards.events.inbound.InitGameEvent;
import coms362.cards.streams.InBoundQueue;
import coms362.cards.streams.RemoteTableGateway;

public class MatchController {

    private ViewFacade views; // empty
    private Table table;
    private Rules rules;
    private InBoundQueue inQ;

    public MatchController(InBoundQueue inQ, Table table, Rules rules, RemoteTableGateway remote,
            GameFactory factory) {
        this.inQ = inQ;
        this.table = table;
        this.rules = rules;
        this.views = new ViewFacade((ViewFactory) factory);
    }

    public void start() {
        // this is match setup ... it depends on which game
        // was selected. We initialize for a new match of the
        // already selected game

        Event e = null;
        while (!table.partiesReady()) {
            try {
                e = inQ.take(); // we are waiting for/looking for new connections
                Move cmd = rules.eval(e, table, table.getCurrentPlayer());
                cmd.apply(table);
                cmd.apply(views);
            } catch (ExitTestException ex) {
                return;
            } catch (Exception ex) {
                // TODO: add support for deferring premature game play events?
                System.out.println("Match Controller exception " + ex.getMessage());
                System.out.println(" ... event = " + e.toString());
            }
        }

        // prime the pump by injecting a generic InitGameEvent()
        Move initCmd = rules.eval(new InitGameEvent(), table, null);
        initCmd.apply(table);
        initCmd.apply(views);

        PlayController mainloop = new PlayController(inQ, rules);
        mainloop.play(table, table.getCurrentPlayer(), views);

    }
}

package coms362.cards.events.inbound;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.RulesDispatch;
import coms362.cards.abstractcomp.Table;

/**
 * Abstract, game-independent handle for all inBound events.
 * 
 * @author Robert
 */
public interface Event {

    public Move dispatch(RulesDispatch rules, Table table, Player player);

}

package coms362.cards.events.inbound;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.RulesDispatch;
import coms362.cards.abstractcomp.Table;

/**
 * This event must never change and must be considered a standard part of every game. 
 * It is not separately factoried, because the game-specific rules are used to factory it. 
 * It does not have a marshaller because it is always created by the MatchController, never by 
 * a user interface element. 
 * 
 * Each set of game rules should respond to this event by returning a Move implementation that
 * knows how to nitialize the state of the game and the table. 
 * 
 * Any change in behavior required from one game to another must be chosen by refering to 
 * game specific information in the rules or captured in the table (e.g., in the quorum object).  
 * There can never be any game specific behavior in this object because there is no 
 * game specific facility for creating it. 
 * 
 * @author Robert Ward
 *
 */
public class InitGameEvent implements Event {

	public static final String kId = "InitGameEvent";

	@Override
	public Move dispatch(RulesDispatch rules, Table table, Player player) {
		return rules.apply(this, table, player);
	}
	


}

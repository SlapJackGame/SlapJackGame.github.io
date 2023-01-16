package coms362.cards.events.inbound;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.RulesDispatch;
import coms362.cards.abstractcomp.Table;
import coms362.cards.model.Quorum;

public class SetQuorumEvent implements Event {

	Quorum quorum = null; 
	
	public SetQuorumEvent(String min, String max) {
		this.quorum = new Quorum(min, max);
	}

	public SetQuorumEvent(Quorum quorum) {
		this.quorum = quorum;
	}

	@Override
	public Move dispatch(RulesDispatch rules, Table table, Player player) {
		return rules.apply(this, table, player);
	}

	public Quorum getQuorum() {
		return quorum;
	}

}

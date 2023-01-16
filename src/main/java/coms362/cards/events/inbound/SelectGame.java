package coms362.cards.events.inbound;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.RulesDispatch;
import coms362.cards.abstractcomp.Table;
import coms362.cards.app.GameController;
import coms362.cards.model.PregameSetup;
import coms362.cards.model.Quorum;

public class SelectGame implements SysEvent, Event {
	
	private String selection;
	private Quorum quorum;
	
	public SelectGame (String gameId){
		selection = gameId;
	}
	
	public SelectGame(String selection, ConnectEvent e) {
		this.selection = selection;
		this.quorum = e.getQuorum();
	}

	public String getSelection(){
		return selection;
	}
	@Override
	public Move dispatch(RulesDispatch rules, Table table, Player player) {
		return rules.apply(this, table, player);
	}

	@Override
	public void accept(GameController handler, PregameSetup game) {
		handler.apply(this, game);		
	}

	public boolean hasQuorum() {
		return (quorum != null && quorum.isSet());
	}

	public Quorum getQuorum() {
		return quorum;
	}

}

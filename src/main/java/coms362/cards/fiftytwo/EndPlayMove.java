package coms362.cards.fiftytwo;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Table;
import coms362.cards.app.ViewFacade;


public class EndPlayMove implements Move {

	public void apply(Table table) {
		table.setMatchOver(true);
		// TODO Auto-generated method stub

	}

	public void apply(ViewFacade view) {	
		// TODO Auto-generated method stub
	}
	
	@Override	
	public boolean isMatchEnd(){
		return true;
	}

}

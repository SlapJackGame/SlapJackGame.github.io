package coms362.cards.fiftytwo;

import coms362.cards.abstractcomp.GameFactory;
import coms362.cards.app.GameFactoryFactory;

public class TestGFF extends GameFactoryFactory {
	public TestGameFactory activeFactory = null; 
	
	public TestGFF(){
		super();
	}
	
	@Override
	public GameFactory getGameFactory(String selector){
		activeFactory = new TestGameFactory( super.getGameFactory(selector));
		return activeFactory;
	}
}

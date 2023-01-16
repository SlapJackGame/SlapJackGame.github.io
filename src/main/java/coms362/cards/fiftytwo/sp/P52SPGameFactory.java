package coms362.cards.fiftytwo.sp;

import coms362.cards.abstractcomp.Rules;
import coms362.cards.fiftytwo.P52MPGameFactory;

public class P52SPGameFactory extends P52MPGameFactory {

	@Override
	public Rules createRules() {
		return new P52SPPickupRules();
	}

}
package coms362.cards.app;
import coms362.cards.model.Button;
import coms362.cards.model.Location;
import coms362.cards.events.inbound.DealEvent;


public class DealButton extends Button{
	public static String kSelector = "DealButton";
	public DealButton(String la, Location lo){
		super(kSelector, DealEvent.kId, la, lo);
	}
}

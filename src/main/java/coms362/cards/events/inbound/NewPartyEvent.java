package coms362.cards.events.inbound;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.RulesDispatch;
import coms362.cards.abstractcomp.Table;
import coms362.cards.app.GameController;
import coms362.cards.fiftytwo.PartyRole;
import coms362.cards.model.PregameSetup;

/**
 * This event is normally synthesized whenever a new connection is 
 * received. Notice that it may be handled differently depending 
 * upon whether it is dispatched as a SysEvent or just Event. 
 * 
 * When dispatched through the SysEvent interface, it will be handled
 * by game-independent rules in GameController. Match and play controller, 
 * however,
 * 
 * @author Robert Ward
 *
 */
public class NewPartyEvent implements SysEvent, Event {

	public static final String kId = "NewPartyEvent";
	
	private PartyRole role;
	private Integer position;
	private String socketId = null;
	

	public NewPartyEvent(PartyRole role, String pnum, String socketId) {
		this.role = role;
		this.position = Integer.valueOf(pnum);
		this.socketId = socketId;
	}

	@Override
	public Move dispatch(RulesDispatch rules, Table table, Player player) {
		return rules.apply(this, table, player);
	}

	@Override
	public void accept(GameController handler, PregameSetup game) {
		handler.apply(this, game);		
	}
	
	public PartyRole getRole(){
		return this.role;
	}
	
	public String getSocketId(){
		return socketId;
	}
	
	public Integer getPosition(){
		return position;
	}

}

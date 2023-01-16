package coms362.cards.events.remote;

import coms362.cards.abstractcomp.Player;
import coms362.cards.streams.Marshalls;

public class SetBottomPlayerTextRemote implements Marshalls, FilterOnOwner {
	
	public String player="Host";
	public Player owner; 
	
	public SetBottomPlayerTextRemote(String name, Player p) {
		player = name;
		owner = p;
	}

	public String marshall() {
		return String.format("$('#current-player').text('%s');\n"
				+ "$('#current-player').append($('<span><span>'));", player);
	}

	public String stringify() {
		return "SetBottomPlayerText "+player;
	}

	@Override
	public boolean isOwnedBy(String viewSocketId) {
		if (owner == null || viewSocketId == null || owner.getSocketId() == null)
			return false;
		else 
			return owner.getSocketId().contentEquals(viewSocketId);
	}

}

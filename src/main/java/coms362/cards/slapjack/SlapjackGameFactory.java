package coms362.cards.slapjack;

import coms362.cards.abstractcomp.GameFactory;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Rules;
import coms362.cards.abstractcomp.Table;
import coms362.cards.abstractcomp.View;
import coms362.cards.abstractcomp.ViewFactory;
import coms362.cards.fiftytwo.PartyRole;
import coms362.cards.model.PlayerFactory;
import coms362.cards.model.TableBase;
import coms362.cards.streams.RemoteTableGateway;

public class SlapjackGameFactory implements GameFactory, PlayerFactory, ViewFactory {

	@Override
	public Rules createRules() {
		return new SlapjackRules();
	}

	@Override
	public Table createTable() {
		return new TableBase(this);
	}

	@Override
	public View createView(PartyRole role, Integer num, String socketId, RemoteTableGateway gw ) {
		return new SlapjackPlayerView(num, socketId, gw);
	}

	@Override
	public Player createPlayer( Integer position, String socketId) {
		return new SlapjackPlayer(position, socketId);
	}

	@Override
	public PlayerFactory createPlayerFactory() {
		return this;
	}

}

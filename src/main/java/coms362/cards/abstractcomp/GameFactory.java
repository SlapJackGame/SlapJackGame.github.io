package coms362.cards.abstractcomp;

import coms362.cards.fiftytwo.PartyRole;
import coms362.cards.model.PlayerFactory;
import coms362.cards.streams.RemoteTableGateway;

/**
 * The interface through which the various controllers acquire game specific components.
 * 
 * @author Robert Ward
 */
public interface GameFactory extends PlayerFactory, ViewFactory {
    
    public Rules createRules();

    public Table createTable();

    public PlayerFactory createPlayerFactory();

    public Player createPlayer(Integer position, String socketId);

    public View createView(PartyRole role, Integer num, String socketId, RemoteTableGateway gw);

}

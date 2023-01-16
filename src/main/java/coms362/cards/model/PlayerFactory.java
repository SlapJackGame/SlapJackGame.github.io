package coms362.cards.model;

import coms362.cards.abstractcomp.Player;

/**
 * An alias and narrowing facade. This is used to enable the 
 * tableBase to create players without risking coupling every table to the 
 * full complement of game-specific components. 
 * 
 * @author Robert Ward
 *
 */
public interface PlayerFactory {

	Player createPlayer(Integer position, String socketId);

}

package coms362.cards.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Table;

/**
 * The game-independent portion of the game state. 
 * New state and operations should be added by subclassing this base or 
 * by otherwise creating a game-specific replacement. Changing the api or 
 * behavior of this class is apt to break other games. 
 * 
 * @author Robert Ward
 */
public class TableBase implements Table {
	
	private Map<String,Pile> piles = new HashMap<String,Pile>();
	// following is indexed by player position (playernum)
	private Map<Integer, Player> players = new HashMap<Integer, Player>();
	private Random rng = new Random();
	private boolean matchOver = false;
	private Quorum quorum = null; 
	private Integer currentPlayer = -1;
	private PlayerFactory playerFactory;
	// following is indexed by socketId
	private Map<String, Player> playerIndex = new HashMap<String,Player>();
	
	public TableBase(PlayerFactory pFactory){
		playerFactory = pFactory;
	}
	
	public void addPile(Pile pile) {
		piles.put(pile.selector, pile);
	}

	public void addPlayer(Player p) {
		players.put(p.getPlayerNum(), p);
        playerIndex.put(p.getSocketId(), p);
	}

	public void apply(Move move) {
		move.apply(this);
	}

	public Pile getPile(String name) {
		return piles.get(name);
	}

	public void removeFromPile(String name, Card c) {
		Pile target = piles.get(name);
		if (target != null){
			target.cards.remove(c.getRemoteId());
		}
	}

	public void addToPile(String name, Card c) {
		Pile target = piles.get(name);
		if (target != null){
			target.cards.put(c.getRemoteId(), c);
		}
	}

	public int addToScore(Player p, int i) {
		System.out.println("Table: addToScore p = "+p);
		Player playn = players.get(p.getPlayerNum());
		if (playn != null) {
			return playn.addToScore(i);
		}
		return 0;
	}

	public Random getRandom() {
		return rng;
	}

	public boolean isMatchOver() {
		return matchOver;
	}

	public void setMatchOver(boolean over) {
		matchOver = over;
		
	}
	public Map<Integer,Player> getPlayerMap(){
		return players;
	}
	
	public Collection<Player> getPlayers(){
		return players.values();
	}

	@Override
	public Party getHost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean partiesReady() {
		return quorum != null && quorum.meets(players.size());
	}

	@Override
	public Player getCurrentPlayer() {
		return players.get(currentPlayer);
	}

	@Override
	public void setQuorum(Quorum quorum) {
		this.quorum = quorum;		
	}

	@Override
	public Quorum getQuorum() {
		return quorum;
	}

	@Override
	public void createPlayer(Integer position, String socketId) {
		Player p = playerFactory.createPlayer(position, socketId );
		System.out.format("Table.createPlayer pos=%d, hash=%d", position, p.hashCode());
		addPlayer(p);
	}

	@Override
	public Player lookupPlayer(String socketId) {
		return playerIndex.get(socketId);
	}

	@Override
	public Player getPlayer(Integer pos) {
		return players.get(pos);
	}

}

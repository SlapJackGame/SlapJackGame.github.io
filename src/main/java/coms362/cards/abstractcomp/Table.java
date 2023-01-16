package coms362.cards.abstractcomp;

import java.util.Collection;
import java.util.Map;
import java.util.Random;

import coms362.cards.model.Card;
import coms362.cards.model.Party;
import coms362.cards.model.Pile;
import coms362.cards.model.Quorum;

/**
 * This interface defines the <em>current</em> game-independent portion of the game-state model. The
 * entities visible through this interface may be analogous to UI elements (such as cardPiles), but
 * in keeping with MVP concepts, one should <em>not</em> equate the logical element here to the UI
 * element and should <em>not</em> add UI or additional game-specific entities to this interface.
 * 
 * It is expected that specific games will extend BaseTable to add game-specific entities and
 * game-specific state operations.
 * 
 * However, remember that only game-specific code (concrete elements) should depend upon other
 * game-specific code, and then only if the client has a mechanism for recovering the specific type
 * of the target.
 *
 * Please note that this is the preferred place to keep track of players (which should probably be
 * extended to Parties).
 * 
 * Warning: some of these methods may not have a meaningful implementation yet. you need to check
 * TableBase.
 * 
 * @author Robert Ward
 */
public interface Table {

    public void addPile(Pile pile);

    public void addPlayer(Player p);

    public Pile getPile(String string);

    public void removeFromPile(String string, Card c);

    public void addToPile(String string, Card c);

    public int addToScore(Player p, int i);

    public boolean isMatchOver();

    public void setMatchOver(boolean over);

    public Random getRandom();

    public Party getHost();

    public boolean partiesReady();

    public Player getCurrentPlayer();

    public void setQuorum(Quorum quorum);

    public Quorum getQuorum();

    public Collection<Player> getPlayers();

    public Map<Integer, Player> getPlayerMap(); // index by playerNum (position)

    public void createPlayer(Integer pos, String socketId);

    public Player lookupPlayer(String socketId);

    public Player getPlayer(Integer pos);

}

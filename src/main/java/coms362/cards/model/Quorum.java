package coms362.cards.model;

/**
 * Contains information about participant count expectations.
 * 
 * For now, it only applies to players. Should we add support for non-playing participants (e.g.,
 * audience, pit boss ...) this will probably need some modification.
 * 
 * min is the minimum number of players required to start. max is the maximum number of players
 * supported.
 * 
 * The quorum is a key part of the game state. In some games, it may be initialized as soon as the
 * game has been selected. in others, it may be managed through a menu, or query parameters.
 * 
 * Presumably, if adding another participant (especially a player) would exceed the maximum, that
 * participant's connection would be refused.
 * 
 * @author Robert Ward
 */
public class Quorum {

    int min = 0;
    int max = 0;

    public Quorum(int minPlayers, int maxPlayers) {
        min = minPlayers;
        max = maxPlayers;
    }

    public Quorum(String minS, String maxS) {
        if (minS != null) {
            min = Integer.valueOf(minS);
        }
        if (maxS != null) {
            max = Integer.valueOf(maxS);
        }
        if (isSet() && min == 0) {
            min = 1;
        }
    }

    public boolean isSet() {
        return (min > 0 || max > 0);
    }

    public boolean meets(int count) {
        return count >= min && count <= max;
    }

    public boolean exceeds(int count) {
        return count > max;
    }

}

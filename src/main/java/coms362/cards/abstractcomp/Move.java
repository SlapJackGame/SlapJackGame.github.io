package coms362.cards.abstractcomp;

import coms362.cards.app.ViewFacade;

/**
 * 
 * An interface that let's the client think of the implementor as an opaque summary of everything
 * that needs to change as a consequence of some input to the play of the game.
 * 
 * Generally the implementing object is created by a method in a game-specific method in rules.
 * 
 * Structurally, the implementing object plays the role of Command in the Command pattern.
 * 
 * The implementor's apply(Table) method is expected to update the local model (game state). The
 * implementor's apply(View) implementation should update the presentation. Thus, in MVP terms, the
 * move object acts remotely on behalf of the controller (playController collaborating with rules)
 * to update both the view and the model.
 * 
 * @author Robert Ward
 * 
 */
public interface Move {

    public void apply(Table table);
    
    public void apply(ViewFacade views);

    default public boolean isMatchEnd() {
        return false;
    }
}

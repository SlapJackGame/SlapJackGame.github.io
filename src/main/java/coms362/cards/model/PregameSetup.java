package coms362.cards.model;

/**
 * This holds application state needed during that portion of application setup that precedes the
 * launch of the game.
 *
 * @author Robert Ward
 */
public class PregameSetup {

    /**
     * Whether a supported game has been selected.
     */
    boolean isSelected = false;
    String selected = "";

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(String selector) {
        selected = selector;
        isSelected = true;
    }

    public String getSelection() {
        return selected;
    }

    public boolean partiesReady() {
        return false;
    }

}

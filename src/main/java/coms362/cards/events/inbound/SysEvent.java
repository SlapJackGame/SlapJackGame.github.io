package coms362.cards.events.inbound;

import coms362.cards.app.GameController;
import coms362.cards.model.PregameSetup;

public interface SysEvent extends Event {

    public void accept(GameController handler, PregameSetup game);

}

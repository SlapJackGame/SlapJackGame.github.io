package coms362.cards.events.remote;

import coms362.cards.model.Button;
import coms362.cards.streams.Marshalls;

public class CreateButtonRemote implements Marshalls {
    private Button button;

    public CreateButtonRemote(Button button) {
        this.button = button;
    }

    public String marshall() {
        return String.format("cards362.createButton('%s', '%s', '%s', %d, %d);\n",
                button.getRemoteId(), button.getEvtName(), button.getLabel(),
                button.getLocation().getX(),
                button.getLocation().getY());
    }

    public String stringify() {
        return "CreateButton " + button.getLabel();
    }
}

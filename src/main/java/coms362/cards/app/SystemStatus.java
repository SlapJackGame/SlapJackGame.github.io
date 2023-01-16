package coms362.cards.app;

import coms362.cards.streams.Marshalls;

public class SystemStatus implements Marshalls {
    private String message;

    public SystemStatus(String msg) {
        this.message = msg;
    }

    @Override
    public String marshall() {
        // TODO: post message to browser (status line or popup?)
        return "";
    }

    @Override
    public String stringify() {
        return "SystemStatus " + message;
    }

}

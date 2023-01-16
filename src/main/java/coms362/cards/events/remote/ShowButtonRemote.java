package coms362.cards.events.remote;

import coms362.cards.streams.Marshalls;

public class ShowButtonRemote implements Marshalls {

    private String remoteId;
    
    public ShowButtonRemote(String remoteId) {
        this.remoteId = remoteId;
    }
    
    @Override
    public String marshall() {
        return String.format("cards362.getById('%s').show();\n", remoteId);          
    }

    @Override
    public String stringify() {
        return String.format("ShowButtonRemote");
    }

}

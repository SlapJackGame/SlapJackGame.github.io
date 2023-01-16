package coms362.cards.slapjack;

import java.io.IOException;

import coms362.cards.abstractcomp.View;
import coms362.cards.events.remote.FilterOnOwner;
import coms362.cards.streams.Marshalls;
import coms362.cards.streams.RemoteTableGateway;

/**
 * @author Robert Ward
 */
public class SlapjackPlayerView implements View {

    private RemoteTableGateway remote;
    private Integer pos;
    private String socketId;

    public SlapjackPlayerView(Integer num, String socketId, RemoteTableGateway remote) {
        this.remote = remote;
        this.socketId = socketId;
        this.pos = num;
    }

    public void send(Marshalls event) throws IOException {
        if (event instanceof FilterOnOwner) {
            if (!((FilterOnOwner) event).isOwnedBy(socketId)) {
                return;
            }
        }
        System.out.format("View %s  sending event %s to socket %s%n", this, event, socketId);
        remote.send(event, socketId);
    }

    @Override
    public int getCameraPosition() {
        return pos;
    }

}

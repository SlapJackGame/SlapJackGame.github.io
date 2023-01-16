package coms362.cards.fiftytwo;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Table;
import coms362.cards.app.ViewFacade;
import coms362.cards.streams.RemoteTableGateway;

public class CreatePlayerCmd implements Move {

    private Integer position;
    private String socketId;

    public CreatePlayerCmd(Integer position, String socketId) {
        super();
        this.position = position;
        this.socketId = socketId;
    }

    @Override
    public void apply(Table table) {
        table.createPlayer(position, socketId);

    }

    @Override
    public void apply(ViewFacade views) {
        RemoteTableGateway gw = RemoteTableGateway.getInstance();
        views.createView(PartyRole.player, position, socketId, gw);

    }

}

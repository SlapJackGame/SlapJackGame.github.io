package coms362.cards.abstractcomp;

import coms362.cards.fiftytwo.PartyRole;
import coms362.cards.streams.RemoteTableGateway;

public interface ViewFactory {

    public View createView(PartyRole role, Integer num, String socketId, RemoteTableGateway gw);

}

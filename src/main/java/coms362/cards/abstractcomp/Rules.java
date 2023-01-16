package coms362.cards.abstractcomp;

import coms362.cards.events.inbound.Event;

public interface Rules {

    Move eval(Event nextE, Table table, Player player);

}

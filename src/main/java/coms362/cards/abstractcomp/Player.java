package coms362.cards.abstractcomp;

import coms362.cards.model.Party;

public interface Player extends Party {

    public int addToScore(int amount);

    public int getPlayerNum();

    public String getSocketId();

    public int getScore();

}

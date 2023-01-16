package coms362.cards.app;

import java.util.Arrays;
import java.util.List;

import coms362.cards.abstractcomp.GameFactory;
import coms362.cards.fiftytwo.P52MPGameFactory;
import coms362.cards.slapjack.SlapjackGameFactory;
import coms362.cards.fiftytwo.sp.P52SPGameFactory;


public class GameFactoryFactory {
    
    // TODO: list of games is hardcoded for now
    static final String PU52MP = "PU52MP";
    static final String PU52SP = "PU52SP";
    static final String Slapjack = "Slapjack";
    String gameIds[] = {PU52MP, PU52SP,Slapjack};
    List<String> supported = Arrays.asList(gameIds);

    
    public GameFactory getGameFactory(String selector) {
        if (selector.equals(PU52MP)) {
            return new P52MPGameFactory();
        } 
        else if (selector.equals(PU52SP)) {
            return new P52SPGameFactory();
        } 
        else if (selector.equals(Slapjack)) {
            return new SlapjackGameFactory();
        }
        return null;
    }

    
    public boolean isValidSelection(String gameId) {
        return supported.contains(gameId);
    }

}

package coms362.cards.model;

public class IdGenerator {
    
    private static int next = 1;
    
    public static synchronized int next() {
        return next++;
    }

}
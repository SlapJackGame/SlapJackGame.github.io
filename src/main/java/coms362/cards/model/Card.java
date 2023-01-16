package coms362.cards.model;

/**
 * @author Robert Ward
 */
// TODO: replace x,y with Location object.
public class Card extends PresentationObject {
    
    public static final String suits[] = {"h", "s", "d", "c"};

    private String suit;
    private int rank;
    private int x;
    private int y;
    private int rotate;
    private boolean faceUp;

    public Card() {
        super();
    }
    
    public Card(String selector) {
        super(selector);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRotate() {
        return rotate;
    }

    public void setRotate(int rotate) {
        this.rotate = rotate;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

}

package coms362.cards.model;

/**
 * A Table-relative position in the View. 
 * 
 * All position information in move objects should 
 * be encoded in this type to be compatible with future view operations. 
 * 
 * @author Robert Ward
 *
 */
public class Location {
	
	int x = 0; 
	int y = 0; 
	
	public Location( ) {
		
	}

	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y; 
	}

}

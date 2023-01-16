package coms362.cards.model;

public class Button extends PresentationObject {

    private String evtName;
    private String label;
    private Location location;

    public Button(String selector, String evtName, String label, Location location) {
    	super(selector);
    	this.evtName = evtName;
        this.label = label;
        this.location = location;
    }
    
    public String getEvtName() {
        return evtName;
    }

    public void setEvtName(String evtName) {
        this.evtName = evtName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}

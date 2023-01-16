package coms362.cards.events.remote;

import coms362.cards.streams.Marshalls;

public class SetupTable implements Marshalls {

	private String id = "#card-table";
	private String url = "js/3rdparty/einaregilsson-cards-js/img/cards.png";
	
	public SetupTable(){
		
	}
	
	public SetupTable(String id, String cardsUrl){
		this.id = id;
		this.url = cardsUrl;
		
	}
		
	public String marshall() {
    	return String.format(
    		"cards.init({table:'%s', cardsUrl:'%s'})",
    		id, url
    	);
	}

	public String stringify() {
		return "SetupTable";
	}

}

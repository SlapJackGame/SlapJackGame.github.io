package coms362.cards.slapjack;


import java.util.Map;
import java.util.Random;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Player;
import coms362.cards.abstractcomp.Table;
import coms362.cards.app.ViewFacade;
import coms362.cards.events.remote.CreateButtonRemote;
import coms362.cards.events.remote.CreatePileRemote;
import coms362.cards.events.remote.SetBottomPlayerTextRemote;
import coms362.cards.events.remote.SetGameTitleRemote;
import coms362.cards.events.remote.SetupTable;
import coms362.cards.events.remote.UpdatePileRemote;
import coms362.cards.fiftytwo.DealButton;
import coms362.cards.fiftytwo.P52Rules;
import coms362.cards.model.Card;
import coms362.cards.model.Location;
import coms362.cards.model.Pile;

public class SlapjackInitCmd implements Move {
    
    private Table table;
	private Map<Integer, Player> players;
	private String title;
	Pile p1;
	Pile p2;

	
	public SlapjackInitCmd(Map<Integer, Player> players, String game, Table table) {
		this.players = players;
		this.title = game;
		this.table = table;
	}
	@Override
	public void apply(Table table){
		Random r = table.getRandom();
		p1 = new Pile(SlapjackRules.player1_pile, new Location(300, 500));
		p2 = new Pile(SlapjackRules.player2_pile, new Location(300, 100));		
		try {
            for (String suit : Card.suits) {
                for (int i = 1; i <= 13; i++) {
                	int n = r.nextInt(100);
                    Card card = new Card();
                    
                    card.setSuit(suit);
                    card.setRank(i);
                    card.setRotate(0);
                    card.setFaceUp(false);
                    
                    if((p1.cards.size() != 26) && (n % 2 == 1)) {
                    	card.setX(p1.getLocation().getX());
                    	card.setY(p1.getLocation().getY());
                    	p1.addCard(card);
                    	
                    	
                    }else {
                    	card.setX(p2.getLocation().getX());
                    	card.setY(p2.getLocation().getY());
                    	p2.addCard(card);  
                    	
                    }
                    
                }
            }
            
            table.addPile(p1);       
            table.addPile(p2);
            table.addPile(new Pile(SlapjackRules.center_pile, new Location(300,300)));
        } catch (Exception e) {
            e.printStackTrace();
        }
		 
        
    }

	public void apply(ViewFacade view) {
		view.send(new SetupTable());
		view.send(new SetGameTitleRemote(title));
		
		for (Player p : players.values()){
			String role1 = (p.getPlayerNum() == 1) ? "Player1" : "Player "+p.getPlayerNum();
			view.send(new SetBottomPlayerTextRemote(role1, p));
		}
		
		view.send(new CreatePileRemote(table.getPile(SlapjackRules.player1_pile)));
		view.send(new CreatePileRemote(table.getPile(SlapjackRules.player2_pile)));
		view.send(new CreatePileRemote(table.getPile(SlapjackRules.center_pile)));
		
		
		//view.send(new UpdatePileRemote(table.getPile(SlapjackRules.player1_pile)));
		//view.send(new UpdatePileRemote(table.getPile(SlapjackRules.player2_pile)));
		DealButton dealButton = new DealButton("DEAL", new Location(0, 0));
		view.register(dealButton); //so we can find it later. 
		view.send(new CreateButtonRemote(dealButton));
		//view.send(new CreateButtonRemote(Integer.toString(getNextId()), "reset", "RestartGame", "Reset", new Location(500,0)));
		//view.send(new CreateButtonRemote(Integer.toString(getNextId()), "clear", "ClearTable", "Clear Table", new Location(500,0)));
	}
	
}



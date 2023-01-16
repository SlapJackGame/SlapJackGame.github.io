package coms362.cards.webapp;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import coms362.cards.socket.CardSocket;
import coms362.cards.socket.CardSocketCreator;

public class CardServlet extends WebSocketServlet {
    private CardSocketCreator cardSocketCreator;
    
    public CardServlet(CardSocketCreator cardSocketCreator) {
        this.cardSocketCreator = cardSocketCreator;
    }
    
    @Override
    public void configure(WebSocketServletFactory factory) {
        //factory.getPolicy().setIdleTimeout(10000);
        factory.register(CardSocket.class);
        factory.setCreator(cardSocketCreator);
    }
}
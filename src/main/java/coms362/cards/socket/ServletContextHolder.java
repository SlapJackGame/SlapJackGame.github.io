package coms362.cards.socket;

import org.eclipse.jetty.servlet.ServletHolder;

import coms362.cards.webapp.CardServlet;

public class ServletContextHolder {
    private ServletHolder servletHolder;
    private String context;
    
    public ServletContextHolder(CardSocketCreator cardSocketCreator, String context) {
        servletHolder = new ServletHolder(new CardServlet(cardSocketCreator));
        this.context = context;
    }

    public ServletHolder getServletHolder() {
        return servletHolder;
    }

    public String getContext() {
        return context;
    }
}

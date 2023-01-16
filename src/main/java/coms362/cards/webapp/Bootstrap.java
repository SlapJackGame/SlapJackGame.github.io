package coms362.cards.webapp;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.webapp.WebAppContext;

import coms362.cards.app.GameController;
import coms362.cards.app.GameFactoryFactory;
import coms362.cards.events.inbound.ConnectEvent;
import coms362.cards.events.inbound.EventUnmarshallers;
import coms362.cards.socket.CardSocketCreator;
import coms362.cards.socket.ServletContextHolder;
import coms362.cards.streams.InBoundQueue;
import coms362.cards.streams.RemoteTableGateway;

/**
 * This class is responsible for launching the embedded webserver and setting up resources the
 * webserver and the main app require.
 * 
 * Aside from possibly adding an event to registerGameRules, students should not normally need to
 * change any of this code.
 * 
 * @author tancreti
 */
public class Bootstrap {

    // TODO: do these, especially the queue, need to be static?
    private static InBoundQueue asyncQ = new InBoundQueue();
    private static EventUnmarshallers eventHandlers = EventUnmarshallers.getInstance();
    // even though eventHandlers is a singleton, we need to pass a ref into the EventConsumer
    // because, events
    // are received in the context of a different class loader from the cardApp logic.
    private static CardSocketCreator socketCreator =
            new CardSocketCreator(new EventConsumer(asyncQ, eventHandlers));
    private static ServletContextHolder context =
            new ServletContextHolder(socketCreator, "/socket");
    private static WebappConfig cardsConfig = new WebappConfig("src/main/cards362app", "/cards362",
            "src/main/webdefault/WEB-INF/webdefault.xml", context);
    private static WebappConfig webappConfigs[] = {cardsConfig};
    private Server server;

    public static void main(String[] args) throws Exception {
        Bootstrap cardApp = new Bootstrap();

        try {
            cardApp.configWebapps(webappConfigs);
            cardApp.startServer();
            System.out.println("Server Started");
            cardApp.startApp(); // UI start

        } catch (Exception e) {
            System.err.println("ERROR starting app server");
            e.printStackTrace();
        }
    }

    public Bootstrap() {
        registerGameRules();
    }

    private void registerGameRules() {
        EventUnmarshallers handlers = EventUnmarshallers.getInstance();
        handlers.registerHandler(ConnectEvent.kId, (Class) ConnectEvent.class);
    }

    public void startServer() throws Exception {
        server.start();
    }

    public void startApp() throws Exception {
        syncWithGateway();
        GameController app = new GameController(asyncQ, RemoteTableGateway.getInstance(),
                new GameFactoryFactory());
        app.run();
        System.out.println("Application Thread exiting");
        synchronized (app) {
            app.notifyAll();
        }
        server.stop();
        server.join();
    }

    public void syncWithGateway() throws InterruptedException {
        int i = 300;
        while (!RemoteTableGateway.getInstance().isReady() && i-- > 0) {
            Thread.sleep(1000);
        }
    }

    public void configWebapps(WebappConfig webappConfigs[]) throws Exception {
        server = new Server(8080);

        HandlerCollection handlers = new HandlerCollection();
        for (WebappConfig config : webappConfigs) {
            configWebapp(config, handlers);
        }
        server.setHandler(handlers);
    }

    private void configWebapp(WebappConfig config, HandlerCollection handlers) {
        WebAppContext webapp = new WebAppContext();
        webapp.setResourceBase(config.getResourceBase());
        webapp.setContextPath(config.getContextPath());
        webapp.setDefaultsDescriptor(config.getDefaultsDescriptor());
        webapp.addServlet(config.getServletContextHolder().getServletHolder(),
                config.getServletContextHolder().getContext());
        handlers.addHandler(webapp);
    }

}

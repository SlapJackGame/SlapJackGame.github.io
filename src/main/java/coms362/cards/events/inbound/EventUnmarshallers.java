package coms362.cards.events.inbound;

import java.util.HashMap;
import java.util.Map;

/**
 * A dynamically configured factory, which constructs the Event instances using introspection to
 * locate a static virtual constructor (or factory method) within one of a previously registered
 * concrete event classes.
 * 
 * Structurally this is a singleton, but it may not be reliably available via static reference from
 * the web server context.
 * 
 * Game-specific logic (often rules) must register all game-specific events before they will be
 * needed. See "registerEvents" in PickupRules.
 * 
 * @author Robert Ward
 */
public class EventUnmarshallers {

    private Map<String, Class<EventFactory>> registry = new HashMap<String, Class<EventFactory>>();

    private static EventUnmarshallers instance = null;

    private EventUnmarshallers() {

    }

    public static synchronized EventUnmarshallers getInstance() {
        if (instance == null) {
            instance = new EventUnmarshallers();
        }
        System.out.println("Creating registry " + instance);
        return instance;
    }

    /**
     * @param eventName a unique string that will be used as the key to lookup the desired event
     *        class
     * @param klass the event.
     */
    public void registerHandler(String eventName, Class<EventFactory> klass) {
        registry.put(eventName, klass);
        System.out.println("registering event " + eventName);
    }

    public Class<EventFactory> getHandler(String eventName) {
        return registry.get(eventName);
    }

}

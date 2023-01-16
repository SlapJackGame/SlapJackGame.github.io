package coms362.cards.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import coms362.cards.abstractcomp.View;
import coms362.cards.abstractcomp.ViewFactory;
import coms362.cards.fiftytwo.PartyRole;
import coms362.cards.model.IdGenerator;
import coms362.cards.model.PresentationObject;
import coms362.cards.streams.Marshalls;
import coms362.cards.streams.RemoteTableGateway;

/**
 * The current presentation state. Also a container for Views -- which function as filters (in the
 * pipes and filters sense) in the flow of commands to the remote gateway.
 * 
 * Any information about the presentation which needs to be saved to enable future operations on
 * dynamically created UI elements should be saved here.
 * 
 * At present the view include only primitive support for command filtering.
 * 
 * @author Robert Ward
 */
public class ViewFacade {

    private ViewFactory factory;
    private List<View> views = new ArrayList<View>();
    private Map<String, String> remoteIdLookup = new HashMap<String, String>();

    public ViewFacade(ViewFactory factory) {
        this.factory = factory;
    }

    public View createView(PartyRole role, Integer pos, String socketId, RemoteTableGateway gw) {
        // TODO Auto-generated method stub
        if (role == PartyRole.player) {
            View v = factory.createView(role, pos, socketId, gw);
            views.add(v);
            return v;
        }
        // todo: define a view for the host and other roles.
        return null;
    }

    public View getDefaultView() {
        return views.get(0);
    }

    public void send(Marshalls cmd) {
        Iterator<View> iter = views.iterator();
        while (iter.hasNext()) {
            View next = iter.next();
            try {
                next.send(cmd);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void add(View view) {
        views.add(view);
    }

    public void register(PresentationObject item) {
        String rval = "";
        remoteIdLookup.put(item.selector, rval = Integer.toString(IdGenerator.next()));
        item.setRemoteId(rval);
    }

    public String getRemoteId(String selector) {
        return remoteIdLookup.get(selector);
    }

}

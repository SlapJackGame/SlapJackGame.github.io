package coms362.cards.fiftytwo;

import coms362.cards.abstractcomp.Move;
import coms362.cards.abstractcomp.Table;
import coms362.cards.app.ViewFacade;
import coms362.cards.model.Quorum;

public class SetQuorumCmd implements Move {

    private Quorum quorum;

    public SetQuorumCmd(Quorum quorum) {
        this.quorum = quorum;
    }

    @Override
    public void apply(Table table) {
        table.setQuorum(quorum);
    }

    @Override
    public void apply(ViewFacade view) {
        // TODO Auto-generated method stub
    }

}

package coms362.cards.abstractcomp;

import java.io.IOException;

import coms362.cards.streams.Marshalls;

public interface View {

	void send(Marshalls event) throws IOException;

	int getCameraPosition();

}

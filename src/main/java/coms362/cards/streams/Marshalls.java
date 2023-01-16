package coms362.cards.streams;

public interface Marshalls {

	public String marshall();

	/**
	 * @return A characteristic message, typically to support test instrumentation. 
	 */
	public String stringify();
}

package lexxer.structures;

/**
 * @created: 10/22/17
 * @since: 0.1
 * @project: MiniComplier
 */
public interface Automaton {

    int getId();

    boolean isStart();

    String getName();

    boolean isEnd();

    Automaton getNextState(String character);

    boolean isValid();

    String getUUID();
}

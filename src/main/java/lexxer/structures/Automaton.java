package lexxer.structures;

/**
 * @created: 10/22/17
 * @since: 0.1
 * @project: MiniComplier
 */
public interface Automaton {

    boolean isStart();

    boolean isEnd();

    Automaton getNextState(String character);

    boolean isValid();


}

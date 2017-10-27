package lexxer.structures.automaton;

import enums.Token;

/**
 * @created: 10/26/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class AutomatonFactory {

    public static Automaton buildAutomaton(Token token, int id, String name) {
        Automaton automaton;

        switch (token) {
            default:
            case GENERIC:
                automaton = new GenericAutomaton(id, name);
                break;
            case ID:
                automaton = new IdAutomaton(id, name);
                break;
            case IF:
                automaton = new IfAutomaton(id, name);
                break;
            case NUM:
                automaton = new NumberAutomaton(id, name);
                break;
            case ERROR:
                automaton = new ErrorAutomaton(id, name);
                break;
        }

        return automaton;
    }

    public static Automaton buildAutomaton(int id, String name) {
        return buildAutomaton(Token.GENERIC, id, name);
    }
}

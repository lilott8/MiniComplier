package lexxer.structures.automaton;

import enums.Token;

/**
 * @created: 10/26/17
 * @since: 0.1
 * @project: MiniComplier
 */
class ErrorAutomaton extends Automaton {

    ErrorAutomaton(int id, String name) {
        super(id, name);
        this.isFinal = true;
        this.token = Token.ERROR;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

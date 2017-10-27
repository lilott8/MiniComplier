package lexxer.structures.automaton;

import enums.Token;

/**
 * @created: 10/26/17
 * @since: 0.1
 * @project: MiniComplier
 */
class GenericAutomaton extends Automaton {

    GenericAutomaton(int id, String name) {
        super(id, name);
        this.isFinal = false;
        this.token = Token.GENERIC;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

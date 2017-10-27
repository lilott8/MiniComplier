package lexxer.structures.automaton;

import enums.Token;

/**
 * @created: 10/26/17
 * @since: 0.1
 * @project: MiniComplier
 */
class IfAutomaton extends Automaton {

    IfAutomaton(int id, String name) {
        super(id, name);
        this.isFinal = true;
        this.token = Token.IF;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

package lexxer.structures.automaton;

import enums.Token;

/**
 * @created: 10/26/17
 * @since: 0.1
 * @project: MiniComplier
 */
class NumberAutomaton extends Automaton {
    NumberAutomaton(int id, String name) {
        super(id, name);
        this.isFinal = true;
        this.token = Token.NUM;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

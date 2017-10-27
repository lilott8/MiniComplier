package lexxer.structures.automaton;

import enums.Token;

/**
 * @created: 10/26/17
 * @since: 0.1
 * @project: MiniComplier
 */
class IdAutomaton extends Automaton {

    IdAutomaton(int id, String name) {
        super(id, name);
        this.isFinal = true;
        this.token = Token.ID;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

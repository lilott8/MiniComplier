package lexxer.structures.automaton;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

import enums.Token;

/**
 * @created: 10/22/17
 * @since: 0.1
 * @project: MiniComplier
 */
public abstract class Automaton {

    protected Token token;
    protected int id;
    protected String name;
    protected String uuid = UUID.randomUUID().toString();
    protected boolean isFinal;

    protected Automaton(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public boolean isFinal() {
        return this.isFinal;
    }

    public String getName() {
        return this.name;
    }

    public String getUUID() {
        return this.uuid;
    }

    public Token getToken() {
        return token;
    }

    public int hashCode() {
        return this.uuid.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Automaton)) {
            return false;
        }

        Automaton edge = (Automaton) obj;
        return StringUtils.equals(uuid, edge.getUUID());
    }

    public String toString() {
        return String.format("%s: id: %d %s", super.toString(), id, token);
    }
}

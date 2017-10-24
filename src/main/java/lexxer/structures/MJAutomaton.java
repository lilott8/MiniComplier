package lexxer.structures;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * @created: 10/22/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class MJAutomaton implements Automaton {

    private int id;
    private boolean isFinal = false;
    private final String uuid = UUID.randomUUID().toString();
    private String name = "";

    public MJAutomaton(int id) {
        this.id = id;
    }

    public MJAutomaton(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public MJAutomaton(int id, boolean end) {
        this.id = id;
        this.isFinal = end;
    }

    public MJAutomaton(int id, boolean end, String name) {
        this.id = id;
        this.isFinal = end;
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public boolean isStart() {
        return false;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public Automaton getNextState(String character) {
        return null;
    }

    @Override
    public boolean isValid() {
        return false;
    }

    public String toString() {
        return String.format("Name: %s ID: %d IsFinal: %b", name, id, isFinal);
    }

    @Override
    public String getUUID() {
        return uuid;
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
}

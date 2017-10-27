package lexxer.structures.edges;

import org.apache.commons.lang3.StringUtils;
import org.jgrapht.graph.DefaultEdge;

import java.util.UUID;

import lexxer.structures.automaton.Automaton;

/**
 * @created: 10/23/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class MJEdge extends DefaultEdge {

    private final String transition;
    private final boolean epsilon;
    private static final long serialVersionUID = 1L;
    private final String uuid = UUID.randomUUID().toString();

    public MJEdge(String edge, boolean epsilon) {
        super();
        this.transition = edge;
        this.epsilon = epsilon;
    }

    public MJEdge(String edge) {
        super();
        this.transition = edge;
        this.epsilon = false;
    }

    @Override
    protected Automaton getSource() {
        return (Automaton) super.getSource();
    }

    @Override
    protected Automaton getTarget() {
        return (Automaton) super.getTarget();
    }


    public String getTransition() {
        return transition;
    }

    public boolean isEpsilon() {
        return this.epsilon;
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
        if (!(obj instanceof MJEdge)) {
            return false;
        }

        MJEdge edge = (MJEdge) obj;
        return StringUtils.equals(uuid, edge.uuid);
    }
}

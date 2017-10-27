package lexxer.structures.edges;

import org.apache.commons.lang3.StringUtils;
import org.jgrapht.graph.DefaultEdge;

import java.util.UUID;

import enums.EdgeEnum;

/**
 * @created: 10/26/17
 * @since: 0.1
 * @project: MiniComplier
 */
public abstract class Edge extends DefaultEdge {

    protected String edgeValue;
    protected EdgeEnum edgeType;
    private static final long serialVersionUID = 1L;
    private final String uuid = UUID.randomUUID().toString();

    Edge() {
        super();
        this.edgeValue = "";
    }

    Edge(String edge) {
        super();
        this.edgeValue = edge;
    }

    @Override
    protected Object getSource() {
        return super.getSource();
    }

    @Override
    protected Object getTarget() {
        return super.getTarget();
    }

    public EdgeEnum getEdgeType() {
        return edgeType;
    }

    public String getEdgeValue() {
        return edgeValue;
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
        // We can keep this abstract because we care about the uuid.
        if (!(obj instanceof Edge)) {
            return false;
        }

        Edge edge = (Edge) obj;
        return StringUtils.equals(uuid, edge.uuid);
    }

    @Override
    public String toString() {
        return String.format("%s: transition: %s", super.toString(), this.edgeType);
    }
}

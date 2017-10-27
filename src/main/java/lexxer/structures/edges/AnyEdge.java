package lexxer.structures.edges;

import enums.EdgeEnum;

/**
 * @created: 10/26/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class AnyEdge extends Edge {

    {
        edgeType = EdgeEnum.ALL_UTF8;
    }

    public AnyEdge() {
        super();
    }

    public AnyEdge(String edge) {
        super(edge);
    }
}

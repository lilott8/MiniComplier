package lexxer.structures.edges;

import enums.EdgeEnum;

/**
 * @created: 10/26/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class EpsilonEdge extends Edge {

    EpsilonEdge() {
        super();
        edgeValue = "EPSILON";
        this.edgeType = EdgeEnum.EPSILON;
    }
}

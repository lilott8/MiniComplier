package lexxer.structures.edges;

import enums.EdgeEnum;

/**
 * @created: 10/26/17
 * @since: 0.1
 * @project: MiniComplier
 */
class NumberEdge extends Edge {

    {
        this.edgeType = EdgeEnum.NUMBERS;
    }

    NumberEdge() {
        super();
    }

    NumberEdge(String number) {
        super();
        this.edgeValue = number;
    }
}

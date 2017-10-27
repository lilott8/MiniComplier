package lexxer.structures.edges;

import enums.EdgeEnum;

/**
 * @created: 10/26/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class CharacterEdge extends Edge {

    CharacterEdge() {
        super(EdgeFactory.ALL_ALPHA);
        edgeType = EdgeEnum.ALL_ALPHA_CHARACTERS;
    }

    CharacterEdge(String edge) {
        super(edge);
        edgeType = EdgeEnum.ALL_ALPHA_CHARACTERS;
    }

    CharacterEdge(String edge, EdgeEnum type) {
        super(edge);
        edgeType = type;
    }
}

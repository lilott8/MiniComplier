package lexxer.structures.edges;

import enums.EdgeEnum;

/**
 * @created: 10/26/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class EdgeFactory {

    public static final String ALL_ALPHA = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String ALL_LOWER_ALPHA = "abcdefghijklmnopqrstuvwxyz";
    public static final String ALL_UPPER_ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String ALL_NUMBERS = "0123456789";

    public static Edge buildEdge(EdgeEnum edgeType) {
        Edge edge;

        switch (edgeType) {
            default:
            case EPSILON:
                edge = new EpsilonEdge();
                break;
            case NUMBERS:
                edge = buildEdge(edgeType, ALL_NUMBERS);
                break;
            case ALL_ALPHA_CHARACTERS:
                edge = buildEdge(edgeType, ALL_ALPHA);
                break;
            case ALPHA_LOWER_CHARACTER:
                edge = buildEdge(edgeType, ALL_LOWER_ALPHA);
                break;
            case ALPHA_UPPER_CHARACTERS:
                edge = buildEdge(edgeType, ALL_UPPER_ALPHA);
                break;
            case ALL_UTF8:
                edge = new AnyEdge();
                break;
        }

        return edge;
    }

    public static Edge buildEdge(EdgeEnum edgeType, String edgeValue) {
        Edge edge;

        switch (edgeType) {
            default:
            case EPSILON:
                edge = new EpsilonEdge();
                break;
            case ALPHA_UPPER_CHARACTERS:
                edge = new CharacterEdge(ALL_UPPER_ALPHA, EdgeEnum.ALPHA_UPPER_CHARACTERS);
                break;
            case NUMBERS:
                edge = new NumberEdge(ALL_NUMBERS);
                break;
            case ALL_ALPHA_CHARACTERS:
                edge = new CharacterEdge(ALL_ALPHA, EdgeEnum.ALL_ALPHA_CHARACTERS);
                break;
            case ALPHA_LOWER_CHARACTER:
                edge = new CharacterEdge(ALL_LOWER_ALPHA, EdgeEnum.ALPHA_LOWER_CHARACTER);
                break;
            case SPECIFIC_CHAR:
                edge = new CharacterEdge(edgeValue, EdgeEnum.SPECIFIC_CHAR);
                break;
            case SET_CHARACTER:
                edge = new CharacterEdge(edgeValue, EdgeEnum.SET_CHARACTER);
                break;
            case ALL_UTF8:
                edge = new AnyEdge();
                break;
        }

        return edge;
    }

}

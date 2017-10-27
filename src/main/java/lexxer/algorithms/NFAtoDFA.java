package lexxer.algorithms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jgrapht.DirectedGraph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import enums.EdgeEnum;
import lexxer.structures.automaton.Automaton;
import lexxer.structures.edges.Edge;

/**
 * @created: 10/25/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class NFAtoDFA implements LexxerAlgo {

    Map<Automaton, Set<Integer>> epsilonClosures = new HashMap<>();
    Map<Automaton, Set<Integer>> dfaEdge = new HashMap<>();

    public static final Logger logger = LogManager.getLogger(NFAtoDFA.class);

    @Override
    public String getName() {
        return "NFA to DFA";
    }

    @Override
    public void run() {

    }

    public void run(DirectedGraph<Automaton, Edge> graph) {
        for (Automaton automaton : graph.vertexSet()) {
            this.epsilonClosures.put(automaton, new HashSet<>());
            for (Edge edge : graph.outgoingEdgesOf(automaton)) {
                if (edge.getEdgeType() == EdgeEnum.EPSILON) {
                    epsilonClosures.get(automaton).add(graph.getEdgeTarget(edge).getId());
                    epsilonClosures.get(automaton).add(automaton.getId());
                }
            }
        }

        convertNFAToDFA(graph);
        printClosures();
        printDFAEdges();
        logger.debug(epsilonClosures);
    }

    private void convertNFAToDFA(DirectedGraph<Automaton, Edge> graph) {
        Set<Automaton> states = graph.vertexSet();
        Automaton initialState;
        for (Automaton state : states) {
            if (state.getId() == 1) {
                initialState = state;
            }
        }


    }

    private void printClosures() {
        for (Map.Entry<Automaton, Set<Integer>> entry : this.epsilonClosures.entrySet()) {
            logger.debug(entry.getKey().getId() + " " + entry.getValue());
        }
    }

    private void printDFAEdges() {
        for (Map.Entry<Automaton, Set<Integer>> entry : this.dfaEdge.entrySet()) {
            logger.warn(entry.getKey().getId() + " " + entry.getValue());
        }
    }

}

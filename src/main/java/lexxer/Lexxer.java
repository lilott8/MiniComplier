package lexxer;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import lexxer.structures.Automaton;
import shared.Phase;

/**
 * @created: 10/22/17
 * @since: 0.1
 * @project: MiniComplier
 */
public abstract class Lexxer implements Phase {

    protected final String PHASE_NAME = "Lexical Analysis";

    protected DirectedGraph<Automaton, DefaultEdge> lexxer = new SimpleDirectedGraph<>(DefaultEdge.class);

    @Override
    public String getPhaseName() {
        return "Lexical Analysis";
    }
}

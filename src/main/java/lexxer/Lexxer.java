package lexxer;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import java.util.ArrayList;
import java.util.List;

import lexxer.structures.Automaton;
import lexxer.structures.MCCharacter;
import shared.Phase;

/**
 * @created: 10/22/17
 * @since: 0.1
 * @project: MiniComplier
 */
public abstract class Lexxer implements Phase {

    protected final String PHASE_NAME = "Lexical Analysis";

    protected List<MCCharacter> characters = new ArrayList<>();

    protected DirectedGraph<Automaton, DefaultEdge> lexxer = new SimpleDirectedGraph<>(DefaultEdge.class);

    @Override
    public String getPhaseName() {
        return "Lexical Analysis";
    }
}

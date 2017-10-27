package lexxer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jgrapht.DirectedGraph;
import org.jgrapht.ext.DOTExporter;
import org.jgrapht.ext.EdgeNameProvider;
import org.jgrapht.ext.IntegerNameProvider;
import org.jgrapht.ext.StringNameProvider;
import org.jgrapht.graph.SimpleDirectedGraph;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lexxer.structures.MCCharacter;
import lexxer.structures.automaton.Automaton;
import lexxer.structures.edges.Edge;
import shared.Phase;

/**
 * @created: 10/22/17
 * @since: 0.1
 * @project: MiniComplier
 */
public abstract class Lexxer implements Phase {

    protected final String PHASE_NAME = "Lexical Analysis";

    protected List<MCCharacter> characters = new ArrayList<>();

    protected DirectedGraph<Automaton, Edge> lexxer = new SimpleDirectedGraph<>(Edge.class);

    private static final Logger logger = LogManager.getLogger(Lexxer.class);

    private int VERTEX_ID = 1;

    @Override
    public String getPhaseName() {
        return "Lexical Analysis";
    }

    protected int getCurrentID() {
        return VERTEX_ID;
    }

    protected int getIDAndIncrement() {
        int result = VERTEX_ID;
        VERTEX_ID += 1;
        return result;
    }

    public void printGraphviz() {
        IntegerNameProvider<Automaton> p1 = new IntegerNameProvider<>();
        StringNameProvider<Automaton> p2 = new StringNameProvider<>();
        EdgeNameProvider<Edge> edgeProvider = new EdgeNameProvider<Edge>() {
            @Override
            public String getEdgeName(Edge mjEdge) {
                return mjEdge.toString();
            }
        };
        DOTExporter<Automaton, Edge> exporter = new DOTExporter<>(p1, p2, edgeProvider);
        try {
            exporter.export(new FileWriter("/Users/jason/Desktop/graph.dot"), this.lexxer);
        } catch (IOException e) {
            logger.error(e);
        }
    }
}

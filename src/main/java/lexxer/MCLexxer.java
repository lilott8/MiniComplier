package lexxer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import config.ConfigFactory;
import enums.EdgeEnum;
import enums.Token;
import io.FileHandler;
import io.MCReader;
import lexxer.algorithms.NFAtoDFA;
import lexxer.structures.MCCharacter;
import lexxer.structures.automaton.Automaton;
import lexxer.structures.automaton.AutomatonFactory;
import lexxer.structures.edges.EdgeFactory;
import shared.Phase;

import static io.FileHandler.NEW_LINE;

/**
 * @created: 10/22/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class MCLexxer extends Lexxer {

    public static final Logger logger = LogManager.getLogger(MCLexxer.class);

    private MCReader reader;

    public MCLexxer() {
        this.reader = new MCReader(ConfigFactory.getConfig().getCompilationFile());

        Automaton start = AutomatonFactory.buildAutomaton(Token.START, 1, "start");
        Automaton generic2 = AutomatonFactory.buildAutomaton(Token.GENERIC, 2, "2");
        Automaton ifId = AutomatonFactory.buildAutomaton(Token.IF, 3, "3");
        Automaton generic4 = AutomatonFactory.buildAutomaton(Token.GENERIC, 4, "4");
        Automaton generic5 = AutomatonFactory.buildAutomaton(Token.GENERIC, 5, "5");
        Automaton generic6 = AutomatonFactory.buildAutomaton(Token.GENERIC, 6, "6");
        Automaton generic7 = AutomatonFactory.buildAutomaton(Token.GENERIC, 7, "7");
        Automaton id = AutomatonFactory.buildAutomaton(Token.ID, 8, "8");
        Automaton generic9 = AutomatonFactory.buildAutomaton(Token.GENERIC, 9, "9");
        Automaton generic10 = AutomatonFactory.buildAutomaton(Token.GENERIC, 10, "10");
        Automaton generic11 = AutomatonFactory.buildAutomaton(Token.GENERIC, 11, "11");
        Automaton generic12 = AutomatonFactory.buildAutomaton(Token.GENERIC, 12, "12");
        Automaton numId = AutomatonFactory.buildAutomaton(Token.NUM, 13, "13");
        Automaton generic14 = AutomatonFactory.buildAutomaton(Token.GENERIC, 14, "14");
        Automaton errorId = AutomatonFactory.buildAutomaton(Token.ERROR, 15, "15");

        this.lexxer.addVertex(start);
        this.lexxer.addVertex(generic2);
        this.lexxer.addVertex(generic4);
        this.lexxer.addVertex(generic9);
        this.lexxer.addVertex(generic14);
        this.lexxer.addVertex(generic5);
        this.lexxer.addVertex(generic6);
        this.lexxer.addVertex(generic7);
        this.lexxer.addVertex(generic10);
        this.lexxer.addVertex(generic11);
        this.lexxer.addVertex(generic12);
        //this.lexxer.addVertex(generic11);
        this.lexxer.addVertex(ifId);
        this.lexxer.addVertex(id);
        this.lexxer.addVertex(numId);
        this.lexxer.addVertex(errorId);
        // If parser.
        this.lexxer.addEdge(start, generic2, EdgeFactory.buildEdge(EdgeEnum.SPECIFIC_CHAR, "i"));
        this.lexxer.addEdge(generic2, ifId, EdgeFactory.buildEdge(EdgeEnum.SPECIFIC_CHAR, "f"));
        // Begins the ID parser.
        this.lexxer.addEdge(start, generic4, EdgeFactory.buildEdge(EdgeEnum.EPSILON));
        // Begins the number/real parser.
        this.lexxer.addEdge(start, generic9, EdgeFactory.buildEdge(EdgeEnum.EPSILON));
        // Begins the comment parser.
        this.lexxer.addEdge(start, generic14, EdgeFactory.buildEdge(EdgeEnum.EPSILON));
        // Finish the ID parser.
        this.lexxer.addEdge(generic4, generic5, EdgeFactory.buildEdge(EdgeEnum.ALL_ALPHA_CHARACTERS));
        this.lexxer.addEdge(generic5, id, EdgeFactory.buildEdge(EdgeEnum.EPSILON));
        this.lexxer.addEdge(id, generic6, EdgeFactory.buildEdge(EdgeEnum.EPSILON));
        this.lexxer.addEdge(generic6, generic7, EdgeFactory.buildEdge(EdgeEnum.ALL_ALPHA_CHARACTERS));
        this.lexxer.addEdge(generic7, id, EdgeFactory.buildEdge(EdgeEnum.EPSILON));
        // Finish the number/real parser.
        this.lexxer.addEdge(generic9, generic10, EdgeFactory.buildEdge(EdgeEnum.NUMBERS));
        this.lexxer.addEdge(generic10, numId, EdgeFactory.buildEdge(EdgeEnum.EPSILON));
        this.lexxer.addEdge(numId, generic11, EdgeFactory.buildEdge(EdgeEnum.EPSILON));
        this.lexxer.addEdge(generic11, generic12, EdgeFactory.buildEdge(EdgeEnum.NUMBERS));
        this.lexxer.addEdge(generic12, numId, EdgeFactory.buildEdge(EdgeEnum.EPSILON));
        // Finish the comment parser.
        this.lexxer.addEdge(generic14, errorId, EdgeFactory.buildEdge(EdgeEnum.ALL_UTF8));
    }

    @Override
    public Phase runPhase() {
        char character;
        boolean looping = true;
        int column = 1;
        int line = 1;
        while (looping) {
            character = (char) this.reader.read();
            if ((int) character == FileHandler.EOF) {
                looping = false;
                this.characters.add(new MCCharacter(line, column, MCCharacter.SPACE));
            } else {
                this.characters.add(new MCCharacter(line, column, character));
                column++;
                if ((int) character == NEW_LINE) {
                    line++;
                    column = 1;
                }
            }
        }
        // Close the file.
        this.reader.closeFile();

        NFAtoDFA algo = new NFAtoDFA();
        algo.run(this.lexxer);
        this.printGraphviz();
        return this;
    }

    @Override
    public String getPhaseName() {
        return "MCLexxer " + super.getPhaseName();
    }
}

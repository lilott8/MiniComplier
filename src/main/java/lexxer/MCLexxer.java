package lexxer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import config.ConfigFactory;
import io.FileHandler;
import io.MCReader;
import lexxer.structures.Automaton;
import lexxer.structures.MCCharacter;
import lexxer.structures.MJAutomaton;
import lexxer.structures.MJEdge;
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

        Automaton start = new MJAutomaton(this.getIDAndIncrement(), "1");
        Automaton generic1 = new MJAutomaton(this.getIDAndIncrement(), "2");
        Automaton ifId = new MJAutomaton(this.getIDAndIncrement(), true, "3");
        Automaton generic2 = new MJAutomaton(this.getIDAndIncrement(), "4");
        Automaton generic3 = new MJAutomaton(this.getIDAndIncrement(), "9");
        Automaton generic4 = new MJAutomaton(this.getIDAndIncrement(), "14");
        Automaton generic5 = new MJAutomaton(this.getIDAndIncrement(), "5");
        Automaton generic6 = new MJAutomaton(this.getIDAndIncrement(), "6");
        Automaton generic7 = new MJAutomaton(this.getIDAndIncrement(), "7");
        Automaton generic8 = new MJAutomaton(this.getIDAndIncrement(), "10");
        Automaton generic9 = new MJAutomaton(this.getIDAndIncrement(), "11");
        Automaton generic10 = new MJAutomaton(this.getIDAndIncrement(), "12");
        //Automaton generic11 = new MJAutomaton(this.getIDAndIncrement(), "");
        Automaton id = new MJAutomaton(this.getIDAndIncrement(), true, "8");
        Automaton numId = new MJAutomaton(this.getIDAndIncrement(), true, "13");
        Automaton errorId = new MJAutomaton(this.getIDAndIncrement(), true, "15");

        this.lexxer.addVertex(start);
        this.lexxer.addVertex(generic1);
        this.lexxer.addVertex(generic2);
        this.lexxer.addVertex(generic3);
        this.lexxer.addVertex(generic4);
        this.lexxer.addVertex(generic5);
        this.lexxer.addVertex(generic6);
        this.lexxer.addVertex(generic7);
        this.lexxer.addVertex(generic8);
        this.lexxer.addVertex(generic9);
        this.lexxer.addVertex(generic10);
        //this.lexxer.addVertex(generic11);
        this.lexxer.addVertex(ifId);
        this.lexxer.addVertex(id);
        this.lexxer.addVertex(numId);
        this.lexxer.addVertex(errorId);
        // If parser.
        this.lexxer.addEdge(start, generic1, new MJEdge("i"));
        this.lexxer.addEdge(generic1, ifId, new MJEdge("f"));
        // Begins the ID parser.
        this.lexxer.addEdge(start, generic2, new MJEdge("*", true));
        // Begins the number/real parser.
        this.lexxer.addEdge(start, generic3, new MJEdge("*", true));
        // Begins the comment parser.
        this.lexxer.addEdge(start, generic4, new MJEdge("*", true));
        // Finish the ID parser.
        this.lexxer.addEdge(generic2, generic5, new MJEdge("a-zA-Z"));
        this.lexxer.addEdge(generic5, id, new MJEdge("*", true));
        this.lexxer.addEdge(id, generic6, new MJEdge("*", true));
        this.lexxer.addEdge(generic6, generic7, new MJEdge("a-zA-Z0-9"));
        this.lexxer.addEdge(generic7, id, new MJEdge("*", true));
        // Finish the number/real parser.
        this.lexxer.addEdge(generic3, generic8, new MJEdge("0-9"));
        this.lexxer.addEdge(generic8, numId, new MJEdge("*", true));
        this.lexxer.addEdge(numId, generic9, new MJEdge("*", true));
        this.lexxer.addEdge(generic9, generic10, new MJEdge("0-9"));
        this.lexxer.addEdge(generic10, numId, new MJEdge("*", true));
        // Finish the comment parser.
        this.lexxer.addEdge(generic4, errorId, new MJEdge("*"));
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
        this.printGraphviz();
        return this;
    }

    @Override
    public String getPhaseName() {
        return "MCLexxer " + super.getPhaseName();
    }
}

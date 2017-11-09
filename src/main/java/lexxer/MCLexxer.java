package lexxer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.StreamTokenizer;

import config.ConfigFactory;
import io.MCReader;


/**
 * @created: 10/22/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class MCLexxer {

    public static final Logger logger = LogManager.getLogger(MCLexxer.class);

    private MCReader reader;

    public MCLexxer(StreamTokenizer tokenizer) {
        this.reader = new MCReader(ConfigFactory.getConfig().getCompilationFile());

        /*
        Use:
        https://en.wikipedia.org/wiki/Interpreter_pattern
        https://www.tutorialspoint.com/design_pattern/interpreter_pattern.htm
        https://sourcemaking.com/design_patterns/composite
        https://sourcemaking.com/design_patterns/interpreter
         */
    }

    public String getName() {
        return "MCLexxer ";
    }
}

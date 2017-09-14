package complier.lexicon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.Map;

import complier.CompilationPhase;
import config.CommonConfig;
import config.ConfigFactory;
import datastructures.tokenizers.SpaceTokenizer;
import datastructures.tokenizers.Tokenizer;
import io.MCReader;

/**
 * @created: 9/13/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class Lexicon implements CompilationPhase {

    public static final Logger logger = LogManager.getLogger(Lexicon.class);

    private CommonConfig config = ConfigFactory.getConfig();
    private Map<Integer, Tokenizer> tokens = new LinkedHashMap<>();
    private int numberOfChars = 0;

    public Lexicon() {
    }

    @Override
    public void runPhase() {
        this.scanFile(config.getCompilationFile());
        if (config.isDebug()) {
            logger.info(this.tokens);
        }
    }

    public void scanFile(String input) {
        MCReader reader = new MCReader(input);
        String line;
        int lineNumber = 0;
        while ((line = reader.getNextLine()) != null) {
            Tokenizer token = new SpaceTokenizer(line, lineNumber);
            numberOfChars += token.getNumberOfChars();
            this.tokens.put(lineNumber, token);
            lineNumber++;
        }
        reader.closeFile();
    }

    public int getNumberOfChars() {
        return this.numberOfChars;
    }

    public int getNumberOfLines() {
        return this.tokens.size();
    }
}

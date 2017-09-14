package complier.lexicon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import complier.lexicon.subphases.MCParser;
import complier.lexicon.subphases.MCScanner;
import complier.lexicon.subphases.Parser;
import complier.lexicon.subphases.Scanner;
import config.CommonConfig;
import config.ConfigFactory;
import datastructures.CompilationPhase;

/**
 * @created: 9/13/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class Lexicon implements CompilationPhase {

    public static final Logger logger = LogManager.getLogger(Lexicon.class);

    private CommonConfig config = ConfigFactory.getConfig();
    private Parser parser = new MCParser();
    private Scanner scanner = new MCScanner();

    public Lexicon() {
    }

    @Override
    public void runPhase() {
        this.scanner.scanFile(config.getCompilationFile());
        this.parser.parse(this.scanner);
        logger.info(this.parser);
        this.parser.stripComments();
        logger.info(this.parser);
    }
}

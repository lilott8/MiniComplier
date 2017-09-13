package complier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import complier.parser.MCParser;
import complier.parser.Parser;
import complier.scanner.MCScanner;
import complier.scanner.Scanner;
import config.CommonConfig;
import config.ConfigFactory;

/**
 * @created: 9/11/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class Compiler {

    private CommonConfig config;
    public static final Logger logger = LogManager.getLogger(Compiler.class);

    private Scanner scanner;
    private Parser parser;

    public Compiler() {
        this.scanner = new MCScanner();
        this.parser = new MCParser();
        this.config = ConfigFactory.getConfig();
    }

    public void compile() {
        if (this.config.isDebug()) {
            logger.trace("Preparing to compile: " + this.config.getCompilationFile());
        }
        this.scanner.scanFile(this.config.getCompilationFile());
        this.parser.parse(this.scanner);
        logger.trace(this.parser);
    }
}

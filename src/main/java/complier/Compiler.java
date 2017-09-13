package complier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    public Compiler() {
        this.scanner = new MCScanner();
        this.config = ConfigFactory.getConfig();
    }

    public void compile() {

        for (String file : this.config.getFilesForCompilation()) {
            if (this.config.isDebug()) {
                logger.trace("Preparing to compile: " + file);
            }
            this.scanner.scanFile(file);
        }
    }
}

package parser.bioscript;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import config.CommonConfig;
import config.ConfigFactory;
import parser.ParseStrategy;
import parser.bioscript.parser.BSParser;
import parser.bioscript.parser.ParseException;
import parser.bioscript.typchecker.BSTypeChecker;
import shared.Strategy;

/**
 * @created: 11/29/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class BioScript implements ParseStrategy {

    public static final Logger logger = LogManager.getLogger(BioScript.class);
    private CommonConfig config = ConfigFactory.getConfig();
    BSParser parser;
    private Strategy typeChecker;

    public BioScript() {
    }

    @Override
    public String getName() {
        return "BioScript Parse Strategy";
    }

    @Override
    public Strategy run() {
        try (InputStream input = new FileInputStream(config.getInputFile())) {
            this.parser = new BSParser(input);
            input.close();
            // We run the type checking inside the parse.
            try {
                typeChecker = new BSTypeChecker(this.parser.Program()).run();
            } catch (ParseException e) {
                logger.error(e);
            }
        } catch (IOException ioe) {
            logger.fatal("Couldn't load the file: " + config.getInputFile());
        }
        return this;
    }

}

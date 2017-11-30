package parser.minijava;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import config.Config;
import config.ConfigFactory;
import parser.ParseStrategy;
import parser.minijava.parser.MJParser;
import parser.minijava.parser.ParseException;
import parser.minijava.typechecker.MJTypeChecker;
import shared.Strategy;

/**
 * @created: 11/29/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class MiniJava implements ParseStrategy {

    public static final Logger logger = LogManager.getLogger(MiniJava.class);
    private Config config = ConfigFactory.getConfig();
    private Strategy typeChecker;

    @Override
    public String getName() {
        return "MiniJava Parse Strategy";
    }

    @Override
    public Strategy run() {
        try (InputStream input = new FileInputStream(config.getInputFile())) {
            MJParser parser = new MJParser(input);
            try {
                typeChecker = new MJTypeChecker(parser.MJProgram()).run();
            } catch (ParseException e) {
                logger.error(e);
            }
        } catch (IOException ioe) {
            logger.fatal("Couldn't load the file: " + config.getInputFile());
        }
        return this;
    }
}

package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import config.Config;
import config.ConfigFactory;
import parser.bioscript.BioScript;
import parser.minijava.MiniJava;
import shared.Phase;
import shared.Strategy;

/**
 * @created: 11/29/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class Parser implements Phase {

    public static final Logger logger = LogManager.getLogger(Parser.class);
    private Strategy parser;
    private Config config = ConfigFactory.getConfig();

    public Parser() {
        switch (config.getLanguage()) {
            default:
            case MINIJAVA:
                parser = new MiniJava();
                break;
            case BIOSCRIPT:
                parser = new BioScript();
                break;
        }
    }

    @Override
    public String getPhaseName() {
        return "Parsing";
    }

    @Override
    public Phase runPhase() {
        try {
            parser.run();
        } catch (IOException e) {
            logger.fatal(e);
        }
        return this;
    }
}

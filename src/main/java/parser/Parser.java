package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import config.CommonConfig;
import config.ConfigFactory;
import parser.bioscript.BioScript;
import parser.minijava.MiniJava;
import shared.Phase;

/**
 * @created: 11/29/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class Parser implements Phase {

    public static final Logger logger = LogManager.getLogger(Parser.class);
    private ParseStrategy parser;
    private CommonConfig config = ConfigFactory.getConfig();

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
        parser.run();
        return this;
    }
}

package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import shared.Language;
import shared.Phase;
import shared.Strategy;

/**
 * @created: 10/22/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class Parser implements Phase {

    protected final String PHASE_NAME = "Parser";

    private static final Logger logger = LogManager.getLogger(Parser.class);

    private Strategy algorithm;

    public Parser() {
        this(Language.MINIJAVA);
    }

    public Parser(Language language) {
        switch (language) {
            case MINIJAVA:
                this.algorithm = new MJParser();
                break;
            case BIOSCRIPT:
                this.algorithm = new BSParser();
                break;
        }
    }

    public Parser(Strategy strategy) {
        this.algorithm = strategy;
    }

    @Override
    public Phase runPhase() {
        this.algorithm.run();
        return this;
    }

    @Override
    public String getPhaseName() {
        return this.algorithm.getName();
    }
}

package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import shared.Strategy;

/**
 * @created: 11/9/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class BSParser implements Strategy {

    public static final Logger logger = LogManager.getLogger(BSParser.class);

    public BSParser() {

    }

    @Override
    public String getName() {
        return "BSParser ";
    }

    @Override
    public void run() {
        logger.info("Running " + getName());
    }
}

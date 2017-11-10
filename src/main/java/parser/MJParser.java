package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import shared.Strategy;


/**
 * @created: 10/22/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class MJParser implements Strategy {

    public static final Logger logger = LogManager.getLogger(MJParser.class);

    public MJParser() {

    }

    @Override
    public void run() {
        logger.info("Running " + getName());
    }

    public String getName() {
        return "MJParser ";
    }
}

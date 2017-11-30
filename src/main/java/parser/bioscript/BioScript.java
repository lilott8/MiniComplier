package parser.bioscript;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;

import config.Config;
import config.ConfigFactory;
import parser.bioscript.parser.BSParser;
import shared.Strategy;

/**
 * @created: 11/29/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class BioScript implements Strategy {

    public static final Logger logger = LogManager.getLogger(BioScript.class);
    private Config config = ConfigFactory.getConfig();

    public BioScript() {
    }

    @Override
    public String getName() {
        return "BioScript Strategy";
    }

    @Override
    public void run() throws IOException {
        BSParser parser = new BSParser(new FileInputStream(config.getInputFile()));
    }
}

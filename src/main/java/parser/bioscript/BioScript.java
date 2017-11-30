package parser.bioscript;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import config.Config;
import config.ConfigFactory;
import parser.ParseStrategy;
import parser.bioscript.parser.BSParser;

/**
 * @created: 11/29/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class BioScript implements ParseStrategy {

    public static final Logger logger = LogManager.getLogger(BioScript.class);
    private Config config = ConfigFactory.getConfig();
    BSParser parser;

    public BioScript() {
    }

    @Override
    public String getName() {
        return "BioScript Strategy";
    }

    @Override
    public void run() throws IOException {
        InputStream input = new FileInputStream(config.getInputFile());
        this.parser = new BSParser(input);
        input.close();
    }
}

package parser.minijava;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;

import config.Config;
import config.ConfigFactory;
import parser.minijava.parser.MJParser;
import shared.Strategy;

/**
 * @created: 11/29/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class MiniJava implements Strategy {

    public static final Logger logger = LogManager.getLogger(MiniJava.class);
    private Config config = ConfigFactory.getConfig();

    @Override
    public String getName() {
        return "MiniJava Strategy";
    }

    @Override
    public void run() throws IOException {
        MJParser parser = new MJParser(new FileInputStream(config.getInputFile()));
    }
}

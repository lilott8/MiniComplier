package parser.minijava;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import config.Config;
import config.ConfigFactory;
import parser.ParseStrategy;
import parser.minijava.ast.Goal;
import parser.minijava.parser.MJParser;
import parser.minijava.parser.ParseException;

/**
 * @created: 11/29/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class MiniJava implements ParseStrategy {

    public static final Logger logger = LogManager.getLogger(MiniJava.class);
    private Config config = ConfigFactory.getConfig();

    @Override
    public String getName() {
        return "MiniJava Strategy";
    }

    @Override
    public void run() throws IOException {
        Goal goal;
        try {
            goal = this.parse();
        } catch (ParseException e) {
            logger.error(e);
        }
    }

    private Goal parse() throws IOException, ParseException {
        InputStream input = new FileInputStream(config.getInputFile());
        MJParser parser = new MJParser(input);
        input.close();
        return parser.Goal();
    }
}

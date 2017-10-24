import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cli.CliWrapper;
import complier.Complier;

/**
 * @created: 9/11/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class Main {

    static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String... args) throws Exception {
        logger.trace("hello, world");

        CliWrapper cli = new CliWrapper();
        cli.parseCommandLine(args);

        Complier compiler = new Complier();
        compiler.compile();
    }
}

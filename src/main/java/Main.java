import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cli.CliWrapper;

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


    }
}

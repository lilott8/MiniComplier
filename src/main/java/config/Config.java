package config;

import org.apache.commons.cli.CommandLine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @created: 9/11/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class Config implements CommonConfig {

    private String outputDir = "output/";
    private final String compile;
    private boolean debug = false;
    public static final Logger logger = LogManager.getLogger(Config.class);

    Config(CommandLine cmd) {
        this.compile = cmd.getOptionValue("compile");

        if (cmd.hasOption("debug")) {
            this.debug = true;
            logger.info("we have debug");
        }

        if (cmd.hasOption("output")) {
            this.outputDir = cmd.getOptionValue("output");
        }
    }

    @Override
    public String getOutputDir() {
        return this.outputDir;
    }

    @Override
    public String getCompilationFile() {
        return this.compile;
    }

    @Override
    public boolean isDebug() {
        return this.debug;
    }
}

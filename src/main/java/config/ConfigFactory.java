package config;

import org.apache.commons.cli.CommandLine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @created: 9/11/17
 * @since: 0.1
 * @project: MiniComplier
 */
public final class ConfigFactory {
    public static final Logger logger = LogManager.getLogger(ConfigFactory.class);

    private static Config config;

    public static Config buildConfig(CommandLine cmd) {
        config = new Config(cmd);
        return config;
    }

    public static Config getConfig() {
        return config;
    }
}

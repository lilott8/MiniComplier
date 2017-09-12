package config;

import org.apache.commons.cli.CommandLine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @created: 9/11/17
 * @since: 0.1
 * @project: MiniComplier
 */
class Config implements CommonConfig {

    private String outputDir = "output/";
    private List<String> compile = new ArrayList<>();
    private boolean debug = false;

    Config(CommandLine cmd) {
        this.compile.addAll(Arrays.asList(cmd.getOptionValues("compile")));

        if (cmd.hasOption("debug")) {
            this.debug = true;
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
    public List<String> getFilesForCompilation() {
        return this.compile;
    }

    @Override
    public boolean isDebug() {
        return this.debug;
    }
}

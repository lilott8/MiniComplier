package cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

import config.CommonConfig;
import config.ConfigFactory;

/**
 * @created: 9/11/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class CliWrapper {

    private CommandLine cmd;
    private CommandLineParser parser = new DefaultParser();
    Logger logger = LogManager.getLogger(this);

    public CliWrapper() {
    }

    public void parseCommandLine(String... args) throws Exception {
        this.cmd = this.parser.parse(this.buildOptions(), args);
        this.initializeEnvironment(this.cmd);
    }

    private void initializeEnvironment(final CommandLine cmd) throws Exception {
        // see if we asked for help...
        if (cmd.hasOption("help")) {
            HelpFormatter hf = new HelpFormatter();
            hf.printHelp("ChemicalCompiler", buildOptions(), true);
            System.exit(0);
        }

        // See if we have the argument we need.
        if (!cmd.hasOption("compile")) {
            throw new Exception("No input file to compile given.");
        }

        // initialize our config object.
        CommonConfig config = ConfigFactory.buildConfig(cmd);

        // add any initializing statements derived from the command line here.
        if (config.getFilesForCompilation().size() == 0) {
            throw new Exception("We have no valid files for input");
        }
    }

    private Options buildOptions() {
        Options options = new Options();

        // File(s) to compile
        String desc = "Compile the source file(s)" +
                "\nUsage: -c /path/to/file_to_compile.phi";
        options.addOption(Option.builder("c").longOpt("compile")
                .desc(desc).hasArgs().required().type(ArrayList.class)
                .argName("compile").build());

        // Testing mode
        desc = "Debug mode" +
                "\nUsage: -d";
        options.addOption(Option.builder("d").longOpt("debug")
                .desc(desc).type(Boolean.class).hasArg(false).required(false)
                .argName("debug").build());

        // Output file option
        desc = "Place output in which directory.  If -t is set, this must be set." +
                "\n Usage: -o /path/to/output/";
        options.addOption(Option.builder("o").longOpt("output")
                .desc(desc).type(String.class).hasArg().required(false)
                .argName("output").build());
        return options;
    }
}

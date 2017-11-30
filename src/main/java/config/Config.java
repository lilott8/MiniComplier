package config;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import shared.Language;
import shared.TypeCheckLevel;

/**
 * @created: 9/11/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class Config implements CommonConfig, ParseConfig {

    public static final Logger logger = LogManager.getLogger(Config.class);

    private String outputDir = "output/";
    private final String compile;
    private boolean debug = false;
    private Language language;
    private TypeCheckLevel typeCheckLevel = TypeCheckLevel.STATIC;

    Config(CommandLine cmd) {
        this.compile = cmd.getOptionValue("compile");
        this.language = setLanguage(this.compile);

        if (cmd.hasOption("debug")) {
            this.debug = true;
            logger.info("we have debug");
        }

        if (cmd.hasOption("output")) {
            this.outputDir = cmd.getOptionValue("output");
        }

        if (cmd.hasOption("typechecker")) {
            switch (Integer.parseInt(cmd.getOptionValue("typechecker"))) {
                case 0:
                    this.typeCheckLevel = TypeCheckLevel.DISABLED;
                    break;
                default:
                case 1:
                    this.typeCheckLevel = TypeCheckLevel.STATIC;
                    break;
                case 2:
                    this.typeCheckLevel = TypeCheckLevel.INFERENCE;
                    break;
            }
        }
    }

    private Language setLanguage(String input) {
        String extension = FilenameUtils.getExtension(input);
        Language result = Language.MINIJAVA;
        if (StringUtils.equalsIgnoreCase(extension, "mj")) {
            result = Language.MINIJAVA;
        } else if (StringUtils.equalsIgnoreCase(extension, "bs")) {
            result = Language.BIOSCRIPT;
        } else {
            logger.warn("Couldn't find parser for: " + extension + ", using minijava for default.");
        }
        return result;
    }

    @Override
    public String getOutputDir() {
        return this.outputDir;
    }

    @Override
    public String getInputFile() {
        return this.compile;
    }

    @Override
    public boolean isDebug() {
        return this.debug;
    }

    @Override
    public Language getLanguage() {
        return this.language;
    }

    @Override
    public TypeCheckLevel getTypeCheckLevel() {
        return this.typeCheckLevel;
    }
}

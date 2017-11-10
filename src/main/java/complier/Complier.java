package complier;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import config.ConfigFactory;
import parser.Parser;
import shared.Language;
import shared.Phase;

/**
 * @created: 10/22/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class Complier {

    public static final Logger logger = LogManager.getLogger(Complier.class);

    private List<Phase> phases = new ArrayList<>();

    public Complier() {
        phases.add(new Parser(getLanguage()));
    }

    private Language getLanguage() {
        String extension = FilenameUtils.getExtension(ConfigFactory.getConfig().getCompilationFile());
        Language result = Language.MINIJAVA;
        if (StringUtils.equalsIgnoreCase(extension, "mj")) {
            result = Language.MINIJAVA;
        } else if (StringUtils.equalsIgnoreCase(extension, "bs")) {
            result = Language.BIOSCRIPT;
        } else {
            logger.warn("Couldn't find parser for: " + extension + ", using minijava.");
        }
        return result;
    }

    public void compile() {
        for (Phase phase : this.phases) {
            logger.info("running: " + phase.getPhaseName());
            phase.runPhase();
        }
    }
}

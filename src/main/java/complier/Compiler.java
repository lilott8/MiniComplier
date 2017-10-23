package complier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import lexxer.MJLexxer;
import shared.Phase;

/**
 * @created: 10/22/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class Compiler {

    public static final Logger logger = LogManager.getLogger(Compiler.class);

    private List<Phase> phases = new ArrayList<>();

    public Compiler() {
        phases.add(new MJLexxer());
    }

    public void compile() {
        for (Phase phase : this.phases) {
            logger.info("running: " + phase.getPhaseName());
        }
    }
}

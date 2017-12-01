package complier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import ir.IntermediateRepresentation;
import parser.Parser;
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
        phases.add(new Parser());
        phases.add(new IntermediateRepresentation());
    }

    public void compile() {
        for (Phase phase : this.phases) {
            logger.info("running: " + phase.getPhaseName());
            phase.runPhase();
        }
    }
}

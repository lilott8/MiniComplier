package lexxer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import shared.Phase;

/**
 * @created: 10/22/17
 * @since: 0.1
 * @project: MiniComplier
 */
public abstract class Lexxer implements Phase {

    protected final String PHASE_NAME = "Lexical Analysis";

    private static final Logger logger = LogManager.getLogger(Lexxer.class);

    // Map each lexeme to their grammar rule(s)

    private int VERTEX_ID = 1;

    protected Lexxer() {

    }

    @Override
    public String getPhaseName() {
        return "Lexical Analysis";
    }

    protected int getCurrentID() {
        return VERTEX_ID;
    }

    protected int getIDAndIncrement() {
        int result = VERTEX_ID;
        VERTEX_ID += 1;
        return result;
    }
}

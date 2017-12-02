package parser.minijava.typechecker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import parser.minijava.ast.Node;
import parser.minijava.visitor.GJNoArguDepthFirst;

/**
 * @created: 11/30/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class MJTypeChecker extends GJNoArguDepthFirst<Node> {

    public static final Logger logger = LogManager.getLogger(MJTypeChecker.class);

    public MJTypeChecker() {
        logger.warn("We are not running typing right now.");
    }
}

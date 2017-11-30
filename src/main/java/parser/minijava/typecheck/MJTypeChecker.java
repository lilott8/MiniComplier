package parser.minijava.typecheck;

import parser.minijava.ast.Goal;
import shared.TypeChecker;

/**
 * @created: 11/30/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class MJTypeChecker extends TypeChecker {

    private Goal goal;

    public MJTypeChecker(Goal goal) {
        this.goal = goal;
        this.runTypeChecker();
    }

    @Override
    protected boolean runTypeInference() {
        return false;
    }

    @Override
    protected boolean runStaticTypeCheck() {
        return false;
    }
}

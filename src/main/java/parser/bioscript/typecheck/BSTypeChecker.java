package parser.bioscript.typecheck;

import parser.bioscript.ast.Program;
import shared.TypeChecker;

/**
 * @created: 11/30/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class BSTypeChecker extends TypeChecker {

    Program program;

    public BSTypeChecker(Program program) {
        this.program = program;
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

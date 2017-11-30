package parser.minijava.typechecker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import parser.minijava.ast.MJProgram;
import shared.Strategy;

/**
 * @created: 11/30/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class MJTypeChecker implements Strategy {

    public static final Logger logger = LogManager.getLogger(MJTypeChecker.class);
    private MJProgram program;

    public MJTypeChecker(MJProgram goal) {
        this.program = goal;
    }

    @Override
    public String getName() {
        return "MiniJava Type Checker Strategy";
    }

    @Override
    public Strategy run() {
        this.buildSymbolTable();
        this.runTypecheck();
        return this;
    }


    private void buildSymbolTable() {

    }

    private void runTypecheck() {
        logger.info("We are assuming all MJ programs are correctly typed right now.");
    }
}

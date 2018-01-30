package parser.bioscript.typchecker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import shared.Strategy;

/**
 * @created: 11/30/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class BSTypeChecker implements Strategy {

    public static final Logger logger = LogManager.getLogger(BSTypeChecker.class);
    Program program;

    public BSTypeChecker(Program program) {
        this.program = program;
    }

    @Override
    public String getName() {
        return "BioScript Type Checker Strategy";
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

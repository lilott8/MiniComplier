package parser.minijava;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import config.ConfigFactory;
import config.ParseConfig;
import enums.TypeCheckLevel;
import ir.frame.Frame;
import ir.memory.Memory;
import ir.translate.Fragments;
import parser.ParseStrategy;
import parser.minijava.ast.MJProgram;
import parser.minijava.parser.MJParser;
import parser.minijava.parser.ParseException;
import parser.minijava.semantics.MJIRTranslator;
import parser.minijava.semantics.MJSymbolTable;
import parser.minijava.semantics.MJTypeChecker;
import shared.Strategy;

/**
 * @created: 11/29/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class MiniJava implements ParseStrategy {

    public static final Logger logger = LogManager.getLogger(MiniJava.class);
    private ParseConfig config = ConfigFactory.getConfig();
    private MJSymbolTable symbolTable;
    private MJTypeChecker typeChecker;
    private MJIRTranslator translator;

    public MiniJava() {
        Frame frame = Frame.buildFrame(new Memory(), new ArrayList<>());
        this.symbolTable = new MJSymbolTable(frame, new Fragments(frame));
    }

    @Override
    public String getName() {
        return "MiniJava Parse Strategy";
    }

    @Override
    public Strategy run() {
        try (InputStream input = new FileInputStream(config.getInputFile())) {
            MJParser parser = new MJParser(input);
            try {
                MJProgram program = parser.MJProgram();
                program.accept(symbolTable);
                logger.info(symbolTable);
                if (this.config.getTypeCheckLevel() != TypeCheckLevel.DISABLED) {
                    typeChecker = new MJTypeChecker();
                    program.accept(typeChecker);
                } else {
                    logger.warn("Type checking is currently disabled.");
                }

                logger.info("If you want to build stack allocation, introduce it here.");
                Frame frame = Frame.buildFrame(new Memory(), new ArrayList<>());
                translator = new MJIRTranslator(frame, new Fragments(frame), symbolTable.getSymbolTable());
                program.accept(translator);

            } catch (ParseException e) {
                logger.error(e);
                e.printStackTrace();
            }
        } catch (IOException ioe) {
            logger.fatal("Couldn't load the file: " + config.getInputFile());
        }
        return this;
    }
}

package io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

import config.CommonConfig;
import config.ConfigFactory;

/**
 * @created: 9/12/17
 * @since: 0.1
 * @project: MiniComplier
 */
public abstract class FileHandler {

    private static final Logger logger = LogManager.getLogger(FileHandler.class);
    protected String fileName;
    protected File fileHandler;
    protected CommonConfig config = ConfigFactory.getConfig();
    // EOF when printing chars to stdout.
    public static final int EOF = 65535;
    // ASCII value for NL
    public static final int NEW_LINE = 10;

    FileHandler(String fileName, Class<? extends FileHandler> clazz) {
        this.fileName = fileName;
    }

}

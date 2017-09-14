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
abstract class FileHandler {

    private static final Logger logger = LogManager.getLogger();
    protected String fileName;
    protected File fileHandler;
    protected CommonConfig config = ConfigFactory.getConfig();

    FileHandler(String fileName, Class<? extends FileHandler> clazz) {
        this.config = ConfigFactory.getConfig();
        this.fileName = fileName;
    }

}
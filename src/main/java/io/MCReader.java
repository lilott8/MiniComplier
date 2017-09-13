package io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

/**
 * @created: 9/12/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class MCReader extends FileHandler {

    public static final Logger logger = LogManager.getLogger(MCReader.class);
    protected BufferedReader reader;

    public MCReader(String fileName) {
        super(fileName, FileHandler.class);
        this.openFile();
    }

    private MCReader openFile() {
        try {
            if (this.config.isDebug()) {
                logger.trace("Opening file to read: " + this.fileName);
            }
            this.fileHandler = new File(this.fileName);
            this.reader = new BufferedReader(new java.io.FileReader(this.fileHandler));
        } catch (IOException e) {
            logger.error(e);
        }
        return this;
    }

    public int read() {
        try {
            return this.reader.read();
        } catch (IOException e) {
            logger.error(e);
        }
        return -1;
    }

    public String getNextLine() {
        try {
            return this.reader.readLine();
        } catch (IOException e) {
            logger.error(e);
        }
        return "";
    }

    public boolean closeFile() {
        try {
            this.reader.close();
            return true;
        } catch (IOException e) {
            logger.error(e);
        }
        return false;
    }
}

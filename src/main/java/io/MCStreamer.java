package io;

import java.io.StreamTokenizer;

/**
 * @created: 10/31/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class MCStreamer extends FileHandler {

    StreamTokenizer tokenizer;

    public MCStreamer(String fileName, Class<? extends FileHandler> clazz) {
        super(fileName, clazz);
    }
}

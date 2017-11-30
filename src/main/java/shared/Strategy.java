package shared;

import java.io.IOException;

/**
 * @created: 10/25/17
 * @since: 0.1
 * @project: MiniComplier
 */
public interface Strategy {

    String getName();

    void run() throws IOException;
}

package config;

import java.util.List;

/**
 * @created: 9/11/17
 * @since: 0.1
 * @project: MiniComplier
 */
public interface CommonConfig {

    String getOutputDir();

    List<String> getFilesForCompilation();

    boolean isDebug();
}

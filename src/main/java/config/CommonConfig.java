package config;

import enums.Language;

/**
 * @created: 9/11/17
 * @since: 0.1
 * @project: MiniComplier
 */
public interface CommonConfig {

    String getOutputDir();

    String getInputFile();

    boolean isDebug();

    Language getLanguage();
}

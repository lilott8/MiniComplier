package config;

import enums.TypeCheckLevel;

/**
 * @created: 11/29/17
 * @since: 0.1
 * @project: MiniComplier
 */
public interface ParseConfig extends CommonConfig {

    TypeCheckLevel getTypeCheckLevel();
}

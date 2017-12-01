package enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @created: 11/30/17
 * @since: 0.1
 * @project: MiniComplier
 */
public enum Scope {
    PUBLIC, PROTECTED, PACKAGE, PRIVATE, INSTANCE, CLASS, LOCAL;

    public static Scope getScopeFromString(String s) {
        if (StringUtils.equalsIgnoreCase(PUBLIC.toString(), s)) {
            return PUBLIC;
        } else if (StringUtils.equalsIgnoreCase(PROTECTED.toString(), s)) {
            return PROTECTED;
        } else if (StringUtils.equalsIgnoreCase(PACKAGE.toString(), s)) {
            return PACKAGE;
        } else if (StringUtils.equalsIgnoreCase(PRIVATE.toString(), s)) {
            return PRIVATE;
        } else if (StringUtils.equalsIgnoreCase(INSTANCE.toString(), s)) {
            return INSTANCE;
        } else if (StringUtils.equalsIgnoreCase(CLASS.toString(), s)) {
            return CLASS;
        } else {
            return LOCAL;
        }
    }
}

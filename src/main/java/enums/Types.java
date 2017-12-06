package enums;

import parser.minijava.ast.Type;

/**
 * @created: 11/30/17
 * @since: 0.1
 * @project: MiniComplier
 */
public enum Types {
    INT, BOOL, ARRAY, IDENTIFIER, STRING;

    public static Types getType(Type t) {
        return IDENTIFIER;
    }
}

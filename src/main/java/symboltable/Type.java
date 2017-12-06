package symboltable;

import enums.Types;

/**
 * @created: 12/5/17
 * @since: 0.1
 * @project: minicomplier
 */
public class Type {

    private Types type;
    private String typeAsString;

    public Type(Types type) {
        this.type = type;
        this.typeAsString = type.toString();
    }

    public Type(String type) {
        this.type = Types.IDENTIFIER;
        this.typeAsString = type;
    }

    public String getTypeAsString() {
        return this.typeAsString;
    }

    public Types getType() {
        return this.type;
    }

    public String toString() {
        return this.typeAsString;
    }


}

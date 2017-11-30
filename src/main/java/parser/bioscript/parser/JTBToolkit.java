package parser.bioscript.parser;

import parser.bioscript.ast.NodeToken;

/**
 * @created: 11/29/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class JTBToolkit {
    static NodeToken makeNodeToken(Token t) {
        return new NodeToken(t.image.intern(), t.kind, t.beginLine, t.beginColumn, t.endLine, t.endColumn);
    }
}

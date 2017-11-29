package minijava.parser;

import minijava.ast.NodeToken;

/**
 * @created: 11/28/17
 * @since: 0.1
 * @project: MiniComplier
 */
class JTBToolkit {
    // static NodeToken makeNodeToken(Token t) {
    static NodeToken makeNodeToken(Token t) {
        return new NodeToken(t.image.intern(), t.kind, t.beginLine, t.beginColumn, t.endLine, t.endColumn);
    }
}
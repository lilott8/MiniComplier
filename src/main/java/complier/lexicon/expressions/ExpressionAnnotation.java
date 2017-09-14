package complier.lexicon.expressions;

/**
 * @created: 9/13/17
 * @since: 0.1
 * @project: MiniComplier
 */
public @interface ExpressionAnnotation {
    String name();

    String type() default "keyword";

}

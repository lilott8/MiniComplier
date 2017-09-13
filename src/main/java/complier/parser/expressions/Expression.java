package complier.parser.expressions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @created: 9/12/17
 * @since: 0.1
 * @project: MiniComplier
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Expression {
    String name();
    String extensionLevel() default "base";
}

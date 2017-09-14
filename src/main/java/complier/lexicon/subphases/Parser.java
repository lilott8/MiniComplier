package complier.lexicon.subphases;

/**
 * @created: 9/12/17
 * @since: 0.1
 * @project: MiniComplier
 */
public interface Parser {
    Parser parse(Scanner scanner);

    Parser stripComments();
}

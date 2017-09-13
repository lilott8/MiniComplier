package complier.scanner;

/**
 * @created: 9/11/17
 * @since: 0.1
 * @project: MiniComplier
 */
public interface Scanner {
    void scanFile(String input);

    int getNumberOfChars();

    int getNumberOfLines();
}

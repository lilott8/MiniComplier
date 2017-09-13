package complier.scanner;

import java.util.LinkedHashSet;

import complier.scanner.datastructures.MCCharacter;

/**
 * @created: 9/11/17
 * @since: 0.1
 * @project: MiniComplier
 */
public interface Scanner {
    void scanFile(String input);

    int getNumberOfChars();

    int getNumberOfLines();

    LinkedHashSet<MCCharacter> getCharacters();
}

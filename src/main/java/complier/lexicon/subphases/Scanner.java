package complier.lexicon.subphases;

import java.util.LinkedHashSet;

import datastructures.MCCharacter;

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

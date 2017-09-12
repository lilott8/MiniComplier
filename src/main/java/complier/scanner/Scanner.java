package complier.scanner;

import complier.scanner.datastructures.Character;

/**
 * @created: 9/11/17
 * @since: 0.1
 * @project: MiniComplier
 */
public interface Scanner {
    void scanFile(String input);

    void addCharacter(Character character);
}

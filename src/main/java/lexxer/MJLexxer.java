package lexxer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import config.ConfigFactory;
import io.FileHandler;
import io.MCReader;
import lexxer.structures.MCCharacter;
import shared.Phase;

import static io.FileHandler.NEW_LINE;

/**
 * @created: 10/22/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class MJLexxer extends Lexxer {

    public static final Logger logger = LogManager.getLogger(MJLexxer.class);

    MCReader reader;

    public MJLexxer() {
        this.reader = new MCReader(ConfigFactory.getConfig().getCompilationFile());
    }

    @Override
    public Phase runPhase() {
        char character;
        boolean looping = true;
        int column = 1;
        int line = 1;
        logger.info("beginning to lex the file");
        while (looping) {
            character = (char) this.reader.read();

            if ((int) character == FileHandler.EOF) {
                looping = false;
                this.characters.add(new MCCharacter(line, column, MCCharacter.SPACE));
            } else {
                this.characters.add(new MCCharacter(line, column, character));
                column++;
                if ((int) character == NEW_LINE) {
                    line++;
                    column = 1;
                }
            }
        }
        // Close the file.
        this.reader.closeFile();
        for (MCCharacter mcchar : this.characters) {
            logger.debug(mcchar);
        }

        return this;
    }

    @Override
    public String getPhaseName() {
        return "MJLexxer " + super.getPhaseName();
    }
}

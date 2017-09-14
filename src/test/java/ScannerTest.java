import org.junit.Before;
import org.junit.Test;

import cli.CliWrapper;
import complier.lexicon.Lexicon;

import static org.junit.Assert.assertEquals;

/**
 * @created: 9/12/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class ScannerTest {

    @Before
    public void createConfig() {
        CliWrapper wrapper = new CliWrapper();
        try {
            wrapper.parseCommandLine("-c", "");
        } catch (Exception e) {
        }
    }

    @Test
    public void emptyFile() {
        Lexicon lexicon = new Lexicon();
        lexicon.scanFile("src/main/resources/testcases/scanner/emptyFile.mc");
        assertEquals(0, lexicon.getNumberOfChars());
        assertEquals(0, lexicon.getNumberOfLines());
    }

    @Test
    public void multipleLineBreaks() {
        Lexicon lexicon = new Lexicon();
        lexicon.scanFile("src/main/resources/testcases/scanner/multipleLineBreaks.mc");
        assertEquals(36, lexicon.getNumberOfChars());
        assertEquals(10, lexicon.getNumberOfLines());
    }

    @Test
    public void onlyLineBreaks() {
        Lexicon lexicon = new Lexicon();
        lexicon.scanFile("src/main/resources/testcases/scanner/lineBreaks.mc");
        assertEquals(0, lexicon.getNumberOfChars());
        assertEquals(9, lexicon.getNumberOfLines());
    }

    @Test
    public void regularFileFormat() {
        Lexicon lexicon = new Lexicon();
        lexicon.scanFile("src/main/resources/testcases/scanner/nonEmptyFile.mc");
        assertEquals(75, lexicon.getNumberOfChars());
        assertEquals(4, lexicon.getNumberOfLines());
    }

    @Test
    public void toyProgram1() {
        Lexicon lexicon = new Lexicon();
        lexicon.scanFile("src/main/resources/testcases/toyProgram.mc");
        assertEquals(60, lexicon.getNumberOfChars());
        assertEquals(5, lexicon.getNumberOfLines());
    }

    @Test
    public void toyProgramWithComments() {
        Lexicon lexicon = new Lexicon();
        lexicon.scanFile("src/main/resources/testcases/toyProgramComment.mc");
        assertEquals(73, lexicon.getNumberOfChars());
        assertEquals(6, lexicon.getNumberOfLines());
    }
}

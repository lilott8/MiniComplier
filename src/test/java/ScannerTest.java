import org.junit.Before;
import org.junit.Test;

import cli.CliWrapper;
import complier.lexicon.subphases.MCScanner;
import complier.lexicon.subphases.Scanner;

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
        Scanner scanner = new MCScanner();
        scanner.scanFile("src/main/resources/testcases/scanner/emptyFile.mc");
        assertEquals(0, scanner.getNumberOfChars());
        assertEquals(1, scanner.getNumberOfLines());
    }

    @Test
    public void multipleLineBreaks() {
        Scanner scanner = new MCScanner();
        scanner.scanFile("src/main/resources/testcases/scanner/multipleLineBreaks.mc");
        assertEquals(51, scanner.getNumberOfChars());
        assertEquals(10, scanner.getNumberOfLines());
    }

    @Test
    public void onlyLineBreaks() {
        Scanner scanner = new MCScanner();
        scanner.scanFile("src/main/resources/testcases/scanner/lineBreaks.mc");
        assertEquals(9, scanner.getNumberOfChars());
        assertEquals(10, scanner.getNumberOfLines());
    }

    @Test
    public void regularFileFormat() {
        Scanner scanner = new MCScanner();
        scanner.scanFile("src/main/resources/testcases/scanner/nonEmptyFile.mc");
        assertEquals(92, scanner.getNumberOfChars());
        assertEquals(4, scanner.getNumberOfLines());
    }

    @Test
    public void toyProgram1() {
        Scanner scanner = new MCScanner();
        scanner.scanFile("src/main/resources/testcases/toyProgram.mc");
        assertEquals(75, scanner.getNumberOfChars());
        assertEquals(5, scanner.getNumberOfLines());
    }

    @Test
    public void toyProgramWithComments() {
        Scanner scanner = new MCScanner();
        scanner.scanFile("src/main/resources/testcases/toyProgramComment.mc");
        assertEquals(92, scanner.getNumberOfChars());
        assertEquals(6, scanner.getNumberOfLines());
    }
}

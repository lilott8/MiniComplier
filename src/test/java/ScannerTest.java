import org.junit.Before;
import org.junit.Test;

import cli.CliWrapper;
import complier.scanner.MCScanner;
import complier.scanner.Scanner;

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
    public void emptyFileSuccess() {
        Scanner scanner = new MCScanner();
        scanner.scanFile("src/main/resources/testcases/scanner/emptyFile.mc");
        assertEquals(0, scanner.getNumberOfChars());
        assertEquals(0, scanner.getNumberOfLines());
    }

    @Test
    public void scanFileSuccess() {
        Scanner scanner = new MCScanner();
        scanner.scanFile("src/main/resources/testcases/scanner/nonEmptyFile.mc");
        assertEquals(85, scanner.getNumberOfChars());
        assertEquals(4, scanner.getNumberOfLines());


    }
}

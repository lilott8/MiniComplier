import org.junit.Before;
import org.junit.Test;

import cli.CliWrapper;

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
    }

    @Test
    public void multipleLineBreaks() {
    }

    @Test
    public void onlyLineBreaks() {
    }

    @Test
    public void regularFileFormat() {
    }

    @Test
    public void toyProgram1() {
    }

    @Test
    public void toyProgramWithComments() {
    }
}

package frame;

/**
 * @created: 12/1/17
 * @since: 0.1
 * @project: MiniComplier
 *
 * This denotes a value who is local to scope.
 */
public class Temp {

    private String localName;
    private static int temporaryCounter = 0;
    public static final String TEMPORARY_NAME = "T";

    public Temp() {
        this.localName = TEMPORARY_NAME + "_" + temporaryCounter;
        temporaryCounter += 1;
    }
}

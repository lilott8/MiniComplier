package complier.parser;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.Map;

import complier.parser.datastructures.Token;
import complier.scanner.MCScanner;
import complier.scanner.Scanner;
import complier.scanner.datastructures.MCCharacter;

/**
 * @created: 9/12/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class MCParser implements Parser {

    public static final Logger logger = LogManager.getLogger(MCParser.class);
    private LinkedHashMap<String, Token> tokens = new LinkedHashMap<>();

    public MCParser() {
        /*
        try {
            InputStream is = getClass().getResourceAsStream("language.properties");
            Properties props = new Properties();
            props.load(is);
            is.close();
        } catch(IOException e) {
            logger.fatal("Could not load properties file");
            logger.fatal(e);
        }
        */
    }

    @Override
    public void parse(Scanner scanner) {
        StringBuilder sb = new StringBuilder();
        Token token = new Token();
        for (MCCharacter c : scanner.getCharacters()) {
            if (!StringUtils.equalsIgnoreCase(c.getCharacter(), StringUtils.SPACE) && !StringUtils.equals(c.getCharacter(), MCScanner.NEWLINE)) {
                token.addToToken(c);
                sb.append(c);
            } else {
                // only add tokens that aren't whitespace!
                if (!StringUtils.isEmpty(sb.toString())) {
                    this.tokens.put(sb.toString(), token);
                }
                token = new Token();
                sb = new StringBuilder();
            }
        }
        this.tokens.put(sb.toString(), token);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Token> entry : this.tokens.entrySet()) {
            sb.append(entry.getValue()).append(System.lineSeparator());
        }
        return sb.toString();
    }
}

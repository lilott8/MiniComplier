package complier.lexicon.subphases;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import datastructures.MCCharacter;
import datastructures.Token;

/**
 * @created: 9/12/17
 * @since: 0.1
 * @project: MiniComplier
 */
public class MCParser implements Parser {

    public static final Logger logger = LogManager.getLogger(MCParser.class);

    /**
     * This maps lines to series of tokens. So line 1 will contain lists of tokens, effectively.
     */
    private Map<Integer, LinkedHashMap<String, Token>> tokens = new HashMap<>();

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
    public Parser parse(Scanner scanner) {
        StringBuilder sb = new StringBuilder();
        Token token = new Token();
        int lineNumber = 1;
        this.tokens.put(lineNumber, new LinkedHashMap<>());
        for (MCCharacter c : scanner.getCharacters()) {
            // Use our characters to denote the line numbers.
            if (c.getLine() != lineNumber) {
                lineNumber = c.getLine();
                this.tokens.put(lineNumber, new LinkedHashMap<>());
            }
            if (!StringUtils.equalsIgnoreCase(c.getCharacter(), StringUtils.SPACE) && !StringUtils.equals(c.getCharacter(), MCScanner.NEWLINE)) {
                token.addToToken(c);
                sb.append(c);
            } else {
                // only add tokens that aren't whitespace!
                if (!StringUtils.isEmpty(sb.toString())) {
                    this.tokens.get(lineNumber).put(sb.toString(), token);
                }
                token = new Token();
                sb = new StringBuilder();
            }
        }
        this.tokens.get(lineNumber).put(sb.toString(), token);
        return this;
    }

    @Override
    public Parser stripComments() {
        List<Integer> remove = new ArrayList<>();
        for (Map.Entry<Integer, LinkedHashMap<String, Token>> map : this.tokens.entrySet()) {
            for (Map.Entry<String, Token> token : map.getValue().entrySet()) {
                if (StringUtils.equals(token.getValue().getCharacterAt(0).getCharacter(), "#")) {
                    remove.add(token.getValue().getCharacterAt(0).getLine());
                }
                // we only care about the first element.
                break;
            }
        }
        for (int x : remove) {
            this.tokens.remove(x);
        }
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, LinkedHashMap<String, Token>> map : this.tokens.entrySet()) {
            for (Map.Entry<String, Token> entry : map.getValue().entrySet()) {
                sb.append(entry.getValue()).append(System.lineSeparator());
            }
        }

        return sb.toString();
    }
}

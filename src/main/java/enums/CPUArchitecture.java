package enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @created: 12/13/17
 * @since: 0.1
 * @project: minicomplier
 */
public enum CPUArchitecture {
    X86, RISC, ARM;

    public static CPUArchitecture getArchitecture(String input) {
        if (StringUtils.equalsIgnoreCase("risc", input)) {
            return RISC;
        } else if (StringUtils.equalsIgnoreCase("arm", input)) {
            return ARM;
        } else {
            return X86;
        }
    }
}

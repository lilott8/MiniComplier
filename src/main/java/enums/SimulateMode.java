package enums;

/**
 * @created: 12/7/17
 * @since: 0.1
 * @project: minicomplier
 */
public enum SimulateMode {
    LINEARIZED_IR,     // Simulator execution with linearized IR
    BASIC_BLOCKS,       // Simulator execution with basic blocks
    TRACE_SCHEDULE,     // Simulator exectuion with the code produced by Trace Scheduling
}

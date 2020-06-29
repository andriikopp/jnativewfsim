package jnativewfsim.util;

import java.util.*;

/**
 * Workflow Log
 * <p>
 * It is a multiset of process task identifiers (labels).
 *
 * @author Andrii Kopp
 */
public class WorkflowLog {
    private Map<WorkflowTrace, Integer> log = new HashMap<>();

    public Map<WorkflowTrace, Integer> getLog() {
        return log;
    }

    public void setLog(Map<WorkflowTrace, Integer> log) {
        this.log = log;
    }

    @Override
    public String toString() {
        return log.toString();
    }
}

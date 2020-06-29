package jnativewfsim.workflow;

import java.util.ArrayList;
import java.util.List;

/**
 * Workflow Trace
 * <p>
 * Represents a sequence of process task identifiers (labels).
 *
 * @author Andrii Kopp
 */
public class WorkflowTrace {
    private List<WorkflowTask> trace = new ArrayList<>();

    public List<WorkflowTask> getTrace() {
        return trace;
    }

    public void setTrace(List<WorkflowTask> trace) {
        this.trace = trace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkflowTrace trace1 = (WorkflowTrace) o;

        return trace != null ? trace.equals(trace1.trace) : trace1.trace == null;
    }

    @Override
    public int hashCode() {
        return trace != null ? trace.hashCode() : 0;
    }

    @Override
    public String toString() {
        return trace.toString();
    }
}

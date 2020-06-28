package jnativewfsim.util;

import java.util.List;

/**
 * Workflow Pattern
 * <p>
 * This class describes a certain workflow pattern (sequence, exclusive or parallel execution of tasks).
 *
 * @author Andrii Kopp
 */
public class WorkflowPattern {
    private List<String> tasksList;

    public WorkflowPattern(List<String> tasksList) {
        this.tasksList = tasksList;
    }

    public List<String> getTasksList() {
        return tasksList;
    }

    public void setTasksList(List<String> tasksList) {
        this.tasksList = tasksList;
    }
}

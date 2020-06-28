package jnativewfsim.util;

import java.util.List;

/**
 * Workflow Construct
 * <p>
 * This class describes a certain workflow pattern (sequence, exclusive or parallel execution of tasks).
 *
 * @author Andrii Kopp
 */
public class WorkflowConstruct {
    private List<String> tasksList;

    public WorkflowConstruct(List<String> tasksList) {
        this.tasksList = tasksList;
    }

    public List<String> getTasksList() {
        return tasksList;
    }

    public void setTasksList(List<String> tasksList) {
        this.tasksList = tasksList;
    }
}

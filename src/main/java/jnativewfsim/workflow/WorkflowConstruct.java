package jnativewfsim.workflow;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkflowConstruct that = (WorkflowConstruct) o;

        return tasksList != null ? tasksList.equals(that.tasksList) : that.tasksList == null;
    }

    @Override
    public int hashCode() {
        return tasksList != null ? tasksList.hashCode() : 0;
    }
}

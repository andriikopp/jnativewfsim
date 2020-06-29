package jnativewfsim.workflow;

import java.util.Arrays;

/**
 * Workflow Task
 * <p>
 * This class describes a single Task as the certain type of a Workflow Pattern.
 *
 * @author Andrii Kopp
 */
public class WorkflowTask extends WorkflowConstruct {

    public WorkflowTask(String label) {
        super(Arrays.asList(label));
    }

    /**
     * Get task label.
     *
     * @return task label.
     */
    public String getLabel() {
        return getTasksList().get(0);
    }

    @Override
    public String toString() {
        return getLabel();
    }
}

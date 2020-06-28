package jnativewfsim.util;

/**
 * Workflow Process
 * <p>
 * A complete collection of workflow constructs that form a business process structure.
 *
 * @author Andrii Kopp
 */
public class WorkflowProcess extends WorkflowConstruct {
    private String title;

    public WorkflowProcess(String title, WorkflowConstruct... constructs) {
        super(WorkflowBuilder.sequence(constructs).getTasksList());

        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

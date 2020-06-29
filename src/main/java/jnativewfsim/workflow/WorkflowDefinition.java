package jnativewfsim.workflow;

/**
 * Workflow Definition
 * <p>
 * Labeling the workflow process definitions.
 *
 * @author Andrii Kopp
 */
public interface WorkflowDefinition {

    /**
     * Contains the workflow process definition and returns itself.
     *
     * @return the process structure.
     */
    WorkflowProcess structure();
}

package jnativewfsim.main.workflows;

import jnativewfsim.workflow.WorkflowDefinition;
import jnativewfsim.workflow.WorkflowProcess;

import static jnativewfsim.workflow.WorkflowBuilder.*;

public class OrderProcess implements WorkflowDefinition {

    @Override
    public WorkflowProcess structure() {
        return process("Order Process",
                sequence(
                        task("Sign in"),
                        task("Order a product")
                ),
                exclusive(probabilities(30, 70),
                        task("Checkbook"),
                        task("Credit card")
                )
        );
    }
}

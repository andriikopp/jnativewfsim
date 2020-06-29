package jnativewfsim.main.workflows;

import jnativewfsim.workflow.WorkflowDefinition;
import jnativewfsim.workflow.WorkflowProcess;

import static jnativewfsim.workflow.WorkflowBuilder.*;
import static jnativewfsim.workflow.WorkflowBuilder.process;
import static jnativewfsim.workflow.WorkflowBuilder.task;

public class DispatchOfGoods implements WorkflowDefinition {

    @Override
    public WorkflowProcess structure() {
        return process("Dispatch of Goods",

                // using probabilities definition for exclusive constructs
                exclusive(probabilities(30, 70),
                        sequence(

                                // using completion order definition for parallel constructs
                                parallel(timing(2, 1, 3),
                                        task("Request Bid Shipping Company 1"),
                                        task("Request Bid Shipping Company 2"),
                                        task("Request Bid Shipping Company 3")
                                ),

                                task("Choose Company")
                        ),
                        task("Normal Post")
                ),
                task("Package Label"),
                task("Determine Insurance"),
                exclusive(probabilities(40, 60),

                        // using sub-process in a described process structure
                        process("Insurance Process",
                                task("Insurance Taken")
                        ),

                        task("Insurance Not Taken")
                ),
                task("Prepare for pickup")
        );
    }
}

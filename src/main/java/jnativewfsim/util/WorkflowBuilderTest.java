package jnativewfsim.util;

import static jnativewfsim.util.WorkflowBuilder.*;

/**
 * Testing Workflow Builder
 *
 * @author Andrii Kopp
 */
public final class WorkflowBuilderTest {
    private WorkflowBuilderTest() {
    }

    public static void main(String[] args) {
        // generate process traces as sequences of tasks
        // organize the process log as a multiset of traces
        WorkflowLog log = new WorkflowLog();

        // run Dispatch Goods business process in order to generate its footprints
        // generate 100 workflow cases
        for (int instance = 1; instance <= 100; instance++) {
            WorkflowProcess process = process("Dispatch of Goods",

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

            // run process tracing
            run(instance, process);

            // store process trace
            trace(log, process);
        }

        System.out.println(log);
    }
}

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

        // run Dispatch Goods business process in order to generate its footprints
        // generate 100 workflow cases
        for (int instance = 1; instance <= 100; instance++)
            run(instance,
                    process("Dispatch of Goods",
                            exclusive(probabilities(30, 70),
                                    sequence(
                                            parallel(
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
                    )
            );
    }
}

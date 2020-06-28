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
                    sequence(
                            exclusive(
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
                            exclusive(
                                    task("Insurance Taken"),
                                    task("Insurance Not Taken")
                            ),
                            task("Prepare for pickup")
                    )
            );
    }
}

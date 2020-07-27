package jnativewfsim.main;

import jnativewfsim.main.workflows.OrderProcess;
import jnativewfsim.workflow.WorkflowLog;
import jnativewfsim.workflow.WorkflowProcess;

import static jnativewfsim.workflow.WorkflowBuilder.run;
import static jnativewfsim.workflow.WorkflowBuilder.trace;

/**
 * Testing Workflow Builder
 *
 * @author Andrii Kopp
 */
public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        // generate process traces as sequences of tasks
        // organize the process log as a multiset of traces
        WorkflowLog log = new WorkflowLog();

        // run business process in order to generate its footprints
        // generate 100 workflow cases
        for (int instance = 1; instance <= 100; instance++) {
            WorkflowProcess process = new OrderProcess().structure();

            // run process tracing
            run(instance, process);

            // store process trace
            trace(log, process);
        }

        System.out.println(log);

        log.saveWorkflowTraces("Order Process");
    }
}

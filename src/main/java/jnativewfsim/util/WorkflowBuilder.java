package jnativewfsim.util;

import java.util.*;

/**
 * Workflow Builder
 * <p>
 * This is the utility class used to build the workflow scheme of a business process.
 * It contains methods to organize sequence, parallel, and exclusive patterns, as well as to create tasks.
 * <p>
 * This class also allows to simulate process execution in order to generate process trace.
 *
 * @author Andrii Kopp
 */
public final class WorkflowBuilder {
    private WorkflowBuilder() {
    }

    private static Random randomGenerator = new Random();

    /**
     * Takes already prepared patterns and organizes them as parallel executed pieces of a business process.
     *
     * @param sequences - the array of workflow patterns to be organized as parallel pieces of a process.
     * @return the parallel tasks workflow pattern.
     */
    public static WorkflowPattern parallel(WorkflowPattern... sequences) {
        List<String>[] array = new List[sequences.length];

        for (int i = 0; i < sequences.length; i++) {
            array[i] = sequences[i].getTasksList();
        }

        List<List<String>> list = Arrays.asList(array);
        Collections.shuffle(list);
        List<String> output = new ArrayList<>();

        for (WorkflowPattern tasks : sequences) {
            output.addAll(tasks.getTasksList());
        }

        return new WorkflowPattern(output);
    }

    /**
     * Takes already prepared patterns and organizes them as exclusive executed pieces of a business process.
     *
     * @param sequences - the array of workflow patterns to be organized as exclusive pieces of a process.
     * @return the exclusive tasks workflow pattern.
     */
    public static WorkflowPattern exclusive(WorkflowPattern... sequences) {
        return sequences[randomGenerator.nextInt(sequences.length)];
    }

    /**
     * Takes already prepared patterns and organizes them as sequentially executed pieces of a business process.
     *
     * @param sequences - the array of workflow patterns to be organized as sequentially pieces of a process.
     * @return the sequence of tasks workflow pattern.
     */
    public static WorkflowPattern sequence(WorkflowPattern... sequences) {
        List<String> output = new ArrayList<>();

        for (WorkflowPattern tasks : sequences) {
            output.addAll(tasks.getTasksList());
        }

        return new WorkflowPattern(output);
    }

    /**
     * Takes task label and creates the workflow task instance, which is also a special type of the workflow pattern.
     *
     * @param label - the label of a task.
     * @return the task as a kind of workflow pattern.
     */
    public static WorkflowTask task(String label) {
        return new WorkflowTask(label);
    }

    /**
     * Runs the workflow passed in order to generate simulated process trace for research purposes.
     *
     * @param instance - the number of process instances (traces) to be generated;
     * @param process  - the workflow to be traced.
     */
    public static void run(int instance, WorkflowPattern process) {
        for (String task : process.getTasksList()) {
            System.out.println(String.format("%d\t%s\t%s", instance, task, System.nanoTime()));
        }
    }
}

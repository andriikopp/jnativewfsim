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

    /**
     * Takes already prepared patterns and organizes them as parallel executed pieces of a business process.
     *
     * @param constructs - the array of workflow patterns to be organized as parallel pieces of a process.
     * @return the parallel tasks workflow pattern.
     */
    public static WorkflowConstruct parallel(WorkflowConstruct... constructs) {
        List<String>[] array = new List[constructs.length];

        for (int i = 0; i < constructs.length; i++) {
            array[i] = constructs[i].getTasksList();
        }

        List<List<String>> list = Arrays.asList(array);
        Collections.shuffle(list);
        List<String> output = new ArrayList<>();

        for (List<String> tasks : list) {
            output.addAll(tasks);
        }

        return new WorkflowConstruct(output);
    }

    /**
     * Takes already prepared patterns and organizes them as exclusive executed pieces of a business process.
     *
     * @param probabilities - the probabilities of execution of each of the exclusive paths;
     * @param constructs    - the array of workflow patterns to be organized as exclusive pieces of a process.
     * @return the exclusive tasks workflow pattern.
     */
    public static WorkflowConstruct exclusive(List<Integer> probabilities, WorkflowConstruct... constructs) {
        if (probabilities.size() != constructs.length) {
            throw new IllegalArgumentException("Probabilities should match the exclusive constructs.");
        }

        int sum = 0;

        for (int probability : probabilities) {
            sum += probability;
        }

        if (sum != 100) {
            throw new IllegalArgumentException("The sum of probabilities should be equal to 100.");
        }

        double rand = Math.floor(Math.random() * 100) + 1;
        int total = 0;

        for (int i = 0; i < probabilities.size(); i++) {
            total += probabilities.get(i);

            if (rand < total) {
                return constructs[i];
            }
        }

        return constructs[constructs.length - 1];
    }

    /**
     * Takes the probabilities of execution of the exclusive process paths.
     *
     * @param values - the values of probability of the respective paths.
     * @return the list of probability values.
     */
    public static List<Integer> probabilities(int... values) {
        List<Integer> output = new ArrayList<>();

        for (int value : values) {
            output.add(value);
        }

        return output;
    }

    /**
     * Takes already prepared patterns and organizes them as sequentially executed pieces of a business process.
     *
     * @param constructs - the array of workflow patterns to be organized as sequentially pieces of a process.
     * @return the sequence of tasks workflow pattern.
     */
    public static WorkflowConstruct sequence(WorkflowConstruct... constructs) {
        List<String> output = new ArrayList<>();

        for (WorkflowConstruct tasks : constructs) {
            output.addAll(tasks.getTasksList());
        }

        return new WorkflowConstruct(output);
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
     * Organizes the collection of workflow constructs/patterns as the complete business process structure.
     *
     * @param title      - the title of a business process;
     * @param constructs - the collection of workflow patterns to be merged.
     * @return the business process structure defined by the given workflow elements.
     */
    public static WorkflowProcess process(String title, WorkflowConstruct... constructs) {
        return new WorkflowProcess(title, constructs);
    }

    /**
     * Runs the workflow passed in order to generate simulated process trace for research purposes.
     *
     * @param instance - the number of process instances (traces) to be generated;
     * @param process  - the workflow to be traced.
     */
    public static void run(int instance, WorkflowProcess process) {
        for (String task : process.getTasksList()) {
            System.out.println(String.format("%d\t%s\t%s\t%s", instance, process.getTitle(), task, System.nanoTime()));
        }
    }
}

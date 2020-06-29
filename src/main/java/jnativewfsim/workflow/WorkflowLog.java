package jnativewfsim.workflow;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.builder.AbstractFlowNodeBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Workflow Log
 * <p>
 * It is a multiset of process task identifiers (labels).
 *
 * @author Andrii Kopp
 */
public class WorkflowLog {
    private Map<WorkflowTrace, Integer> log = new HashMap<>();

    public Map<WorkflowTrace, Integer> getLog() {
        return log;
    }

    /**
     * Saves process traces as BPMN files to the directory named as the given parameter.
     *
     * @param processTitle - the process name after which the folder with BPMN files is be named.
     */
    public void saveWorkflowTraces(String processTitle) {
        int traceNum = 1;

        for (WorkflowTrace trace : log.keySet()) {
            AbstractFlowNodeBuilder builder = Bpmn.createProcess()
                    .name(processTitle)
                    .startEvent();

            for (WorkflowTask task : trace.getTrace()) {
                builder = builder.userTask().name(task.getLabel());
            }

            BpmnModelInstance modelInstance = builder.endEvent().done();

            try {
                if (!Files.exists(Paths.get("./traces/" + processTitle))) {
                    new File("./traces/" + processTitle).mkdirs();
                }

                Files.write(Paths.get("./traces/" + processTitle + "/" + traceNum + ".bpmn"),
                        Bpmn.convertToString(modelInstance).getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Cannot write trace #" + traceNum + " to BPMN document.\n" + e);
            }

            traceNum++;
        }
    }

    public void setLog(Map<WorkflowTrace, Integer> log) {
        this.log = log;
    }

    @Override
    public String toString() {
        return log.toString();
    }
}

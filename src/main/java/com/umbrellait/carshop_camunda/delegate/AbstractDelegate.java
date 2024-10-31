package com.umbrellait.carshop_camunda.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * Base abstract Camunda Java Delegate class that handles logic for performing
 * business logic and error handling.
 *
 * @author artem.tereshchenko
 *
 */

@Slf4j
public abstract class AbstractDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) {
        try {
            log.trace("Delegate {} was called for process {}",
                    getClass().getSimpleName(),
                    delegateExecution.getProcessInstanceId());
            run(delegateExecution);
        } catch (Exception exception) {
            log.error("Error occurred", exception);
            executeHandling(delegateExecution, exception);
        }
    }

    public void executeHandling(DelegateExecution delegateExecution, Exception exception) {
        throw new BpmnError("PROCESS_ERROR", exception);
    }

    public abstract void run(DelegateExecution delegateExecution);
}
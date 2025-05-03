package org.example.operations.processors;

import org.example.operations.ConsoleOperationType;
import org.example.operations.OperationCommandProcessor;
import org.springframework.stereotype.Component;

@Component
public class ExitProcessor implements OperationCommandProcessor {
    public ExitProcessor() {
    }

    @Override
    public void processOperation() {
        System.exit(130);
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.EXIT;
    }

}

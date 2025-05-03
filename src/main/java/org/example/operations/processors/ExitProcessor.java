package org.example.operations.processors;

import org.example.operations.ConsoleOperationType;
import org.example.operations.OperationCommandProcessor;

public class ExitProcessor implements OperationCommandProcessor {
    public ExitProcessor() {
    }

    @Override
    public void processOperation() {
        System.exit(0);
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.EXIT;
    }

}

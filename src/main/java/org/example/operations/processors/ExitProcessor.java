package org.example.operations.processors;

import org.example.App;
import org.example.operations.ConsoleOperationType;
import org.example.operations.OperationCommandProcessor;
import org.springframework.stereotype.Component;

@Component
public class ExitProcessor implements OperationCommandProcessor {
    public ExitProcessor() {
    }

    @Override
    public void processOperation() {
        App.shutdown();
        System.exit(0);
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.EXIT;
    }

}

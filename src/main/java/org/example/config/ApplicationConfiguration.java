package org.example.config;

import org.example.listener.OperationConsoleListener;
import org.example.operations.ConsoleOperationType;
import org.example.operations.OperationCommandProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

@Configuration
@PropertySource("classpath:/application.properties")
//2:16:55
public class ApplicationConfiguration {

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public OperationConsoleListener operationConsoleListener(
            Scanner scanner,
            List<OperationCommandProcessor> commandProcessors
    ) {
        Map<ConsoleOperationType, OperationCommandProcessor> operationCommandProcessors = commandProcessors
                .stream()
                .collect(Collectors.toMap(
                                OperationCommandProcessor::getOperationType,
                                processor -> processor
                        )
                );
        return new OperationConsoleListener(scanner, operationCommandProcessors);
    }
}

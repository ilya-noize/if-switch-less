package org.example.operations.config;

import org.example.operations.processors.UserCreateProcessor;
import org.example.operations.processors.UserShowAllProcessor;
import org.example.operations.processors.WalletCloseProcessor;
import org.example.operations.processors.WalletCreateProcessor;
import org.example.operations.processors.WalletDepositProcessor;
import org.example.operations.processors.WalletTransferProcessor;
import org.example.operations.processors.WalletWithdrawProcessor;
import org.example.service.UserService;
import org.example.service.WalletService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class OperationProcessorsConfiguration {

    @Bean
    public UserCreateProcessor createUserProcessor(Scanner scanner, UserService userService) {
        return new UserCreateProcessor(scanner, userService);
    }

    @Bean
    public WalletCreateProcessor createWalletProcessor(Scanner scanner, UserService userService, WalletService walletService) {
        return new WalletCreateProcessor(scanner, userService, walletService);
    }

    @Bean
    public UserShowAllProcessor showAllUserProcessor(UserService userService) {
        return new UserShowAllProcessor(userService);
    }

    @Bean
    public WalletDepositProcessor walletDepositProcessor(Scanner scanner, WalletService walletService) {
        return new WalletDepositProcessor(scanner, walletService);
    }

    @Bean
    public WalletWithdrawProcessor walletWithdrawProcessor(Scanner scanner, WalletService walletService) {
        return new WalletWithdrawProcessor(scanner, walletService);
    }

    @Bean
    public WalletTransferProcessor walletTransferProcessor(Scanner scanner, WalletService walletService) {
        return new WalletTransferProcessor(scanner, walletService);
    }

    @Bean
    public WalletCloseProcessor closeWalletProcessor(Scanner scanner, UserService userService, WalletService walletService) {
        return new WalletCloseProcessor(scanner, userService, walletService);
    }
}

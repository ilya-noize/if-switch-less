package org.example.operations;

public enum ConsoleOperationType {
    /**
     * Создание пользователя
     */
    USER_CREATE,
    /**
     * Показать всех пользователей
     */
    SHOW_ALL_USERS,
    /**
     * Создать кошелёк у пользователя
     */
    WALLET_CREATE,
    /**
     * Закрыть кошелёк пользователя
     */
    WALLET_CLOSE,
    /**
     * Пополнить кошелёк пользователя
     */
    WALLET_DEPOSIT,
    /**
     * Перевод с кошелька пользователя на другой кошелёк другому пользователю или самому себе
     */
    WALLET_TRANSFER,
    /**
     * Перемещение между кошельками пользователя
     */
    WALLET_WITHDRAW,
    /**
     * Выход
     */
    EXIT
}

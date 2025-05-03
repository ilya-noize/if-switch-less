package org.example.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WalletProperties {
    private final int defaultAmount;

    private final double transferCommission;

    public WalletProperties(
            @Value(value = "${wallet.default-amount}") int defaultAmount,
            @Value(value = "${wallet.transfer-commission}") double transferCommission
    ) {
        this.defaultAmount = defaultAmount;
        this.transferCommission = transferCommission;
    }

    public int getDefaultAmount() {
        return defaultAmount;
    }

    public double getTransferCommission() {
        return transferCommission;
    }
}

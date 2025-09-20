package com.wallet.wallet.DTO;


import com.wallet.wallet.model.OperationType;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.UUID;


public class PayOperationDTO {

    @NotBlank
    private final UUID uuidWallet;
    @NotBlank
    private final OperationType type;
    @NotBlank
    private final int amount;

    public PayOperationDTO(UUID uuidWallet, OperationType type, int amount) {
        this.uuidWallet = uuidWallet;
        this.type = type;
        this.amount = amount;
    }

    public UUID getUuidWallet() {
        return uuidWallet;
    }

    public OperationType getOperationType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }
}

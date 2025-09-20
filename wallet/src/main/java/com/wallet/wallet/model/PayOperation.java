package com.wallet.wallet.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "pay_operation")
public class PayOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private final UUID payId;
    @Enumerated(EnumType.STRING)
    private OperationType operationType;
    private int amount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    public PayOperation(OperationType operationType, int amount) {
        this.payId = UUID.randomUUID();
        this.operationType = operationType;
        this.amount = amount;
    }

    public PayOperation() {
        this.payId = UUID.randomUUID();
    }
}

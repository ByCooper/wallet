package com.wallet.wallet.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private final UUID uuid;
    private int balance;
    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL)
    private final Set<PayOperation> pay;

    public Wallet(int balance, Set<PayOperation> pay) {
        this.pay = pay;
        this.uuid = UUID.randomUUID();
        this.balance = balance;
    }
}

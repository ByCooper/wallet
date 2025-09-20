package com.wallet.wallet.service;


import com.wallet.wallet.DTO.PayOperationDTO;
import com.wallet.wallet.mapper.MapperPayOperationDTO;
import com.wallet.wallet.model.OperationType;
import com.wallet.wallet.model.Wallet;
import com.wallet.wallet.repository.PayRepository;
import com.wallet.wallet.repository.WalletRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WalletServiceImpl implements WalletService{

    private final WalletRepository wallet;
    private final PayRepository pay;
    private final EntityManager entityManager;

    public WalletServiceImpl(WalletRepository wallet, PayRepository pay, EntityManager entityManager) {
        this.wallet = wallet;
        this.pay = pay;
        this.entityManager = entityManager;
    }

    @Override
    public Wallet getChangeBalance(PayOperationDTO order) {
        if(order.getOperationType().equals(OperationType.DEPOSIT)){
            getDepositMoney(order);
            return wallet.findById(order.getUuidWallet()).orElseThrow(() -> new RuntimeException("Кошелек не найден"));
        }
        else if(order.getOperationType().equals(OperationType.WITHDRAW)){
            getWithdrawMoney(order);
            return wallet.findById(order.getUuidWallet()).orElseThrow(() -> new RuntimeException("Кошелек не найден"));
        } else {
            throw new RuntimeException("Неизвестный тип операции. Повторите попытку");
        }
    }

    @Override
    public int getBalance(UUID uuidWallet) {
        if(wallet.existsById(uuidWallet)) {
            throw new RuntimeException("Кошелек не найден");
        }
        return wallet.findById(uuidWallet).orElseThrow(() -> new RuntimeException("Кошелек не найден")).getBalance();
    }


    private void getDepositMoney(PayOperationDTO order) {
        if(wallet.existsById(order.getUuidWallet())) {
            throw new RuntimeException("Кошелек не найден");
        }
        Wallet wall = entityManager.find(Wallet.class, order.getUuidWallet());
        if(wall != null){
            wall.setBalance(wall.getBalance() + order.getAmount());
        } else {
            throw new RuntimeException("Кошелек не найден " + order.getUuidWallet());
        }
        pay.save(MapperPayOperationDTO.PayOperationDTOToPayOperation(order));
    }


    private void getWithdrawMoney(PayOperationDTO order) {
        if(wallet.existsById(order.getUuidWallet())) {
            throw new RuntimeException("Кошелек не найден");
        }
        Wallet wall = entityManager.find(Wallet.class, order.getUuidWallet());
        if(wall != null){
            if(wall.getBalance() >= order.getAmount()) {
                wall.setBalance(wall.getBalance() - order.getAmount());
            } else {
                throw new RuntimeException("Недостаточно средств на балансе кошелька " + order.getUuidWallet());
            }
        } else {
            throw new RuntimeException("Кошелек не найден " + order.getUuidWallet());
        }
        pay.save(MapperPayOperationDTO.PayOperationDTOToPayOperation(order));
    }
}

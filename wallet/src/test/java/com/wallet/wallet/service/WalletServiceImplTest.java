package com.wallet.wallet.service;

import com.wallet.wallet.DTO.PayOperationDTO;
import com.wallet.wallet.model.OperationType;
import com.wallet.wallet.model.PayOperation;
import com.wallet.wallet.model.Wallet;
import com.wallet.wallet.repository.PayRepository;
import com.wallet.wallet.repository.WalletRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WalletServiceImplTest {

    @InjectMocks
    WalletServiceImpl walletService;

    @Mock
    PayRepository payRepository;

    @Mock
    WalletRepository walletRepository;

    @Mock
    EntityManager entityManager;


    @Test
    void Test_getChangeBalance_Withdraw() {
        //Подготовка ввода данных
        Set<PayOperation> pay = new HashSet<>();
        Wallet wallet = new Wallet(1000, pay);
        PayOperationDTO payOperationDTO_withdraw = new PayOperationDTO(wallet.getUuid(), OperationType.WITHDRAW, 300);

        //Подготовка ожидаемого результата
        when(entityManager.find(Wallet.class, wallet.getUuid())).thenReturn(wallet);
        wallet.setBalance(700);
        when(walletRepository.findById(any())).thenReturn(Optional.of(wallet));
        Wallet actualMinus = walletService.getChangeBalance(payOperationDTO_withdraw);

        //Запуск теста

        assertEquals(wallet, actualMinus);

    }

    @Test
    void Test_getChangeBalance_Deposit() {
        //Подготовка ввода данных
        Set<PayOperation> pay = new HashSet<>();
        Wallet wallet = new Wallet(1000, pay);
        PayOperationDTO payOperationDTO_deposit = new PayOperationDTO(wallet.getUuid(), OperationType.DEPOSIT, 300);

        //Подготовка ожидаемого результата
        when(entityManager.find(Wallet.class, wallet.getUuid())).thenReturn(wallet);
        when(walletRepository.findById(any())).thenReturn(Optional.of(wallet));
        wallet.setBalance(1300);
        Wallet actualPlus = walletService.getChangeBalance(payOperationDTO_deposit);

        //Запуск теста

        assertEquals(wallet, actualPlus);

    }

    @Test
    void Test_getChangeBalance_Exception(){
        //Подготовка ввода данных
        Set<PayOperation> pay = new HashSet<>();
        Wallet wallet = new Wallet(1000, pay);
        PayOperationDTO payOperationDTO = new PayOperationDTO(wallet.getUuid(), OperationType.WITHDRAW, 3000);
        UUID actual = UUID.randomUUID();
        PayOperationDTO payOperationDTO1 = new PayOperationDTO(actual, OperationType.DEPOSIT, 500);

        //Подготовка ожидаемого результата
        when(walletRepository.findById(any())).thenReturn(Optional.of(wallet));
        when(entityManager.find(Wallet.class, wallet.getUuid())).thenReturn(wallet);
        Exception exception_2 = assertThrows(RuntimeException.class, () -> walletService.getChangeBalance(payOperationDTO1));
        Exception exception_3 = assertThrows(RuntimeException.class, () -> walletService.getChangeBalance(payOperationDTO));

        //Запуск теста
        assertEquals("Недостаточно средств на балансе кошелька " + payOperationDTO.getUuidWallet(), exception_3.getMessage());
        assertThrows(RuntimeException.class, () -> walletService.getChangeBalance(payOperationDTO));
    }

    @Test
    void Test_getChangeBalance_Exception_1() {
        //Подготовка ввода данных
        Set<PayOperation> pay = new HashSet<>();
        Wallet wallet = new Wallet(1000, pay);
        PayOperationDTO payOperationDTO = new PayOperationDTO(wallet.getUuid(), OperationType.WITHDRAW, 3000);
        UUID actual = UUID.randomUUID();
        PayOperationDTO payOperationDTO1 = new PayOperationDTO(actual, OperationType.DEPOSIT, 500);

        //Подготовка ожидаемого результата
        Exception exception_2 = assertThrows(RuntimeException.class, () -> walletService.getChangeBalance(payOperationDTO1));

        //Запуск теста
        assertEquals("Кошелек не найден " + payOperationDTO1.getUuidWallet(), exception_2.getMessage());
    }

    @Test
    void Test_getBalance() {
        //Подготовка ввода данных
        Set<PayOperation> pay = new HashSet<>();
        Wallet wallet = new Wallet(1000, pay);

        //Подготовка ожидаемого результата
        when(walletRepository.findById(any())).thenReturn(Optional.of(wallet));
        int actual = walletService.getBalance(wallet.getUuid());

        //Запуск теста
        assertEquals(wallet.getBalance(), actual);
    }

    @Test
    void Test_getBalance_Exception() {
        //Подготовка ввода данных
        Set<PayOperation> pay = new HashSet<>();
        Wallet wallet = new Wallet(1000, pay);

        //Подготовка ожидаемого результата
        UUID uuid = UUID.randomUUID();
        Exception exception = assertThrows(RuntimeException.class, () -> walletService.getBalance(uuid));

        //Запуск теста
        assertEquals("Кошелек не найден", exception.getMessage());
    }
}
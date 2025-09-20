package com.wallet.wallet.service;

import com.wallet.wallet.DTO.PayOperationDTO;
import com.wallet.wallet.model.Wallet;

import java.util.UUID;

public interface WalletService {
    Wallet getChangeBalance(PayOperationDTO order);
    int getBalance(UUID uuidWallet);
}

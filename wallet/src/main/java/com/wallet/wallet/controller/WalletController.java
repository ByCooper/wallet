package com.wallet.wallet.controller;

import com.wallet.wallet.DTO.PayOperationDTO;
import com.wallet.wallet.model.Wallet;
import com.wallet.wallet.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class WalletController {

    private final WalletService service;

    public WalletController(WalletService service) {
        this.service = service;
    }

    @PostMapping("/wallet")
    public ResponseEntity<Wallet> getChangeBalance(@RequestBody PayOperationDTO pay){
        Wallet wallet = service.getChangeBalance(pay);
        return ResponseEntity.ok(wallet);
    }

    @GetMapping("/wallets/{WALLET_UUID}")
    public ResponseEntity<Integer> getBalance(@PathVariable("WALLET_UUID") UUID uuidWallet){
        return ResponseEntity.ok(service.getBalance(uuidWallet));
    }
}

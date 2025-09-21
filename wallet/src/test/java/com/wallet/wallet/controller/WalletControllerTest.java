package com.wallet.wallet.controller;

import com.wallet.wallet.DTO.PayOperationDTO;
import com.wallet.wallet.mapper.MapperPayOperationDTO;
import com.wallet.wallet.model.OperationType;
import com.wallet.wallet.model.PayOperation;
import com.wallet.wallet.model.Wallet;
import com.wallet.wallet.repository.PayRepository;
import com.wallet.wallet.repository.WalletRepository;
import com.wallet.wallet.service.WalletService;
import org.assertj.core.api.Assertions;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(WalletController.class)
class WalletControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private WalletRepository walletRepository;
//
//    @Mock
//    private PayRepository payRepository;
//
//    @Spy
//    private WalletService service;
//
//    @InjectMocks
//    private WalletController controller;

    @Test
    void Test_getChangeBalance() throws Exception {
//        JSONObject wallet = new JSONObject();
//        UUID uuid = UUID.randomUUID();
//        wallet.put("uuid", uuid);
//        wallet.put("balance", 1000);
//
//        PayOperationDTO pay = new PayOperationDTO(uuid, OperationType.DEPOSIT, 1000);
//        Set<PayOperation> set = new HashSet<>();
//        set.add(MapperPayOperationDTO.PayOperationDTOToPayOperation(pay));
//        Wallet walletActual = new Wallet(1000, set);
//
//        when(walletRepository.findById(any(UUID.class))).thenReturn(Optional.of(walletActual));
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .post("/wallet")
//                .content(wallet.toString())
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());


    }

    @Test
    void Test_getBalance() throws Exception {
    }
}
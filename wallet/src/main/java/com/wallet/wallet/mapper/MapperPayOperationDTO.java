package com.wallet.wallet.mapper;

import com.wallet.wallet.DTO.PayOperationDTO;
import com.wallet.wallet.model.PayOperation;

public class MapperPayOperationDTO {
    public static PayOperation PayOperationDTOToPayOperation(PayOperationDTO payOperationDTO){
        PayOperation payOperation = new PayOperation();
        payOperation.setOperationType(payOperationDTO.getOperationType());
        payOperation.setAmount(payOperationDTO.getAmount());
        return payOperation;
    }
}

package edu.miu.cs590.mapper;

import edu.miu.cs590.dto.PurchaseRequest;
import edu.miu.cs590.dto.TxRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PurchaseRequestTxDto {

    @Mapping(source = "paymentMethodId", target = "paymentMethod")
    TxRequestDto toTransaction(PurchaseRequest request);
}

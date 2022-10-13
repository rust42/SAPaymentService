package edu.miu.cs590.mapper;

import edu.miu.cs590.dto.CreditCardDto;
import edu.miu.cs590.dto.PaypalAccountDto;
import edu.miu.cs590.entity.CreditCard;
import edu.miu.cs590.entity.PaypalAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper
public interface PaymentMethodDtoMapper extends EntityMapper {
    @Mapping(source = "method", target = "methodType", defaultValue = "CreditCard")
    CreditCardDto map(CreditCard creditCard);

    @Mapping(source = "method", target = "methodType", defaultValue = "CreditCard")
    PaypalAccountDto map(PaypalAccount paypalAccount);
}

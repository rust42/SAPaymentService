package edu.miu.cs590.mapper;

import edu.miu.cs590.dto.CreditCardDto;
import edu.miu.cs590.dto.PaypalAccountDto;
import edu.miu.cs590.entity.CreditCard;
import edu.miu.cs590.entity.PaypalAccount;

public interface EntityMapper {
    CreditCardDto map(CreditCard creditCard);
    PaypalAccountDto map(PaypalAccount paypalAccount);
}

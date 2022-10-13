package edu.miu.cs590.mapper;

import edu.miu.cs590.dto.CreditCardRequest;
import edu.miu.cs590.dto.PaypalRequest;
import edu.miu.cs590.entity.CreditCard;
import edu.miu.cs590.entity.PaypalAccount;
import org.mapstruct.Mapper;

@Mapper
public interface PaymentMethodMapper {
    CreditCard toCreditCard(CreditCardRequest creditCardRequest);
    PaypalAccount toPaypal(PaypalRequest paypalRequest);
}

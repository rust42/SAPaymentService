package edu.miu.cs590.service;

import edu.miu.cs590.dto.*;

import java.util.List;

public interface PaymentMethodService {
    List<PaymentMethodDto> getAllByUsername(String username);

    CreditCardDto addCreditCard(CreditCardRequest request);
    PaypalAccountDto addPaypal(PaypalRequest request);

    void setDefaultPaymentMethod(long id);

    void delete(long id);
}

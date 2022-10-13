package edu.miu.cs590.controller;


import edu.miu.cs590.dto.CreditCardRequest;
import edu.miu.cs590.dto.PaymentMethodDto;
import edu.miu.cs590.dto.PaypalRequest;
import edu.miu.cs590.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment_methods")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodService paymentMethodService;

    @GetMapping
    public List<PaymentMethodDto> getAll() {
        return paymentMethodService.getAll();
    }

    @PostMapping("/add_credit_card")
    public PaymentMethodDto addCreditCard(@RequestBody CreditCardRequest creditCardRequest) {
        return paymentMethodService.addCreditCard(creditCardRequest);
    }

    @PostMapping("/add_paypal")
    public PaymentMethodDto addPaypal(@RequestBody PaypalRequest paypalRequest) {
        return paymentMethodService.addPaypal(paypalRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteMethod(@PathVariable long id) {
        paymentMethodService.delete(id);
    }

}

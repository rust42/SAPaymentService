package edu.miu.cs590.service;

import edu.miu.cs590.dto.*;
import edu.miu.cs590.entity.CreditCard;
import edu.miu.cs590.entity.PaypalAccount;
import edu.miu.cs590.mapper.*;
import edu.miu.cs590.repository.PaymentMethodRepo;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PaymentMethodService {

    @Autowired
    private EntityMapper paymentMethodMapper;
    @Autowired
    private PaymentMethodRepo paymentMethodRepo;

    public List<PaymentMethodDto> getAll() {
        List<PaymentMethodDto> methods = new ArrayList<>();
        paymentMethodRepo.findAll().forEach(method -> {
            if (method instanceof MappableEntity me) {
                PaymentMethodDto dto = me.map(paymentMethodMapper);
                methods.add(dto);
            } else {
                throw new RuntimeException("Invalid payment method type");
            }
        });
        return methods;
    }

    public CreditCardDto addCreditCard(CreditCardRequest request) {
        CreditCard creditCard = paymentMethodRepo.save(Mappers.getMapper(PaymentMethodMapper.class).toCreditCard(request));
        return Mappers.getMapper(CreditCardDtoMapper.class).toDto(creditCard);
    }
    public PaypalAccountDto addPaypal(PaypalRequest request) {
        PaypalAccount paypalAccount = paymentMethodRepo.save(Mappers.getMapper(PaymentMethodMapper.class).toPaypal(request));
        return Mappers.getMapper(PaypalAccountDtoMapper.class).toDto(paypalAccount);
    }

    public void delete(long id) {
        paymentMethodRepo.deleteById(id);
    }

}

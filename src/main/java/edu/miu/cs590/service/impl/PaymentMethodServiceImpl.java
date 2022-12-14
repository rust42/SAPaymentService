package edu.miu.cs590.service.impl;

import edu.miu.cs590.dto.*;
import edu.miu.cs590.entity.CreditCard;
import edu.miu.cs590.entity.PaymentMethod;
import edu.miu.cs590.entity.PaypalAccount;
import edu.miu.cs590.exceptions.PaymentMethodNotFoundException;
import edu.miu.cs590.mapper.MappableEntity;
import edu.miu.cs590.mapper.PaymentMethodDtoMapper;
import edu.miu.cs590.mapper.PaymentMethodMapper;
import edu.miu.cs590.repository.PaymentMethodRepo;
import edu.miu.cs590.service.PaymentMethodService;
import edu.miu.cs590.util.AppUtil;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class PaymentMethodServiceImpl implements PaymentMethodService {

    @Autowired
    private PaymentMethodDtoMapper paymentMethodMapper;
    @Autowired
    private PaymentMethodRepo paymentMethodRepo;

    public List<PaymentMethodDto> getAllByUsername(String username) {
        List<PaymentMethodDto> methods = new ArrayList<>();
        paymentMethodRepo.findAllByUsername(username).forEach(method -> {
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
        CreditCard creditCard = Mappers.getMapper(PaymentMethodMapper.class).toCreditCard(request);
        creditCard = paymentMethodRepo.save(creditCard);

        creditCard.setUsername(AppUtil.getCurrentUser());
        long paymentMethodsCount = paymentMethodRepo.count();
        if (paymentMethodsCount == 1) {
            creditCard.setDefault(true);
        }
        return Mappers.getMapper(PaymentMethodDtoMapper.class).map(creditCard);
    }
    public PaypalAccountDto addPaypal(PaypalRequest request) {
        PaypalAccount paypalAccount = Mappers.getMapper(PaymentMethodMapper.class).toPaypal(request);
        paypalAccount.setUsername(AppUtil.getCurrentUser());
        paypalAccount = paymentMethodRepo.save(paypalAccount);
        return Mappers.getMapper(PaymentMethodDtoMapper.class).map(paypalAccount);
    }

    public void setDefaultPaymentMethod(long id) {
        String username = AppUtil.getCurrentUser();
        Optional<PaymentMethod> optional = paymentMethodRepo.findByIdAndUsername(id, username);

        if(optional.isEmpty()) {
            throw new PaymentMethodNotFoundException("No such payment method found");
        }

        PaymentMethod paymentMethod = optional.get();

        paymentMethodRepo.findAllDefaultByUsername(username)
                .forEach(method -> method.setDefault(false));
        paymentMethod.setDefault(true);
    }

    public void delete(long id) {
        paymentMethodRepo.deleteById(id);
    }
}

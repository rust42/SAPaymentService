package edu.miu.cs590.service.impl;

import edu.miu.cs590.dto.PaymentMethodDto;
import edu.miu.cs590.dto.PurchaseRequest;
import edu.miu.cs590.dto.PurchaseResponse;
import edu.miu.cs590.dto.PurchaseStatus;
import edu.miu.cs590.entity.PaymentMethod;
import edu.miu.cs590.entity.PaymentType;
import edu.miu.cs590.exceptions.PaymentMethodNotFoundException;
import edu.miu.cs590.mapper.MappableEntity;
import edu.miu.cs590.mapper.PaymentMethodDtoMapper;
import edu.miu.cs590.repository.PaymentMethodRepo;
import edu.miu.cs590.service.PurchaseService;
import edu.miu.cs590.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PaymentMethodRepo paymentMethodRepo;

    @Autowired
    private PaymentMethodDtoMapper mapper;

    @Override
    public PurchaseResponse purchaseFailurePlaceholder(PurchaseRequest request) {
        PaymentMethod method = getPaymentMethod(request);
        PaymentMethodDto dto;

        if (method instanceof MappableEntity me) {
            dto = me.map(mapper);
        } else {
            throw  new PaymentMethodNotFoundException("Invalid payment method.");
        }

        return PurchaseResponse.builder()
                .orderId(request.getOrderId())
                .paymentAmount(request.getAmount())
                .purchaseStatus(PurchaseStatus.FAILED)
                .failureReason(Optional.of("Purchase could not be validated."))
                .paymentMethod(dto)
                .build();
    }

    @Override
    public PurchaseResponse purchase(PurchaseRequest request) {
        PaymentMethod method = getPaymentMethod(request);
        PaymentMethodDto dto;

        if (method instanceof MappableEntity me) {
            dto = me.map(mapper);
        } else {
            throw  new PaymentMethodNotFoundException("Invalid payment method.");
        }

        processPayment(dto, request);

        return PurchaseResponse.builder()
                .orderId(request.getOrderId())
                .paymentAmount(request.getAmount())
                .purchaseStatus(PurchaseStatus.SUCCESS)
                .paymentMethod(dto)
                .build();
    }


    // Dummy placeholder for processing the payment
    private void processPayment(PaymentMethodDto paymentMethod, PurchaseRequest request) {
        if(paymentMethod.getMethodType().equals(PaymentType.Value.CREDIT_CARD)) {
            System.out.println("Processing order with Credit card");
        } else if (paymentMethod.getMethodType().equals(PaymentType.Value.PAYPAL)) {
            System.out.println("Processing order with Paypal");
        }
    }

    private PaymentMethod getPaymentMethod(PurchaseRequest request) {
        String user = AppUtil.getCurrentUser();

        Optional<PaymentMethod> optionalMethod = paymentMethodRepo.findByUsernameAndId(user, request.getPaymentMethodId());

        if (optionalMethod.isEmpty()) {
            throw new PaymentMethodNotFoundException("No such payment found exist.");
        }
        return optionalMethod.get();
    }
}

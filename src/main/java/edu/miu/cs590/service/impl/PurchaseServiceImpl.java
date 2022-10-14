package edu.miu.cs590.service.impl;

import edu.miu.cs590.client.TxServiceClient;
import edu.miu.cs590.dto.*;
import edu.miu.cs590.entity.PaymentMethod;
import edu.miu.cs590.entity.PaymentType;
import edu.miu.cs590.exceptions.PaymentMethodNotFoundException;
import edu.miu.cs590.mapper.MappableEntity;
import edu.miu.cs590.mapper.PaymentMethodDtoMapper;
import edu.miu.cs590.mapper.PurchaseRequestTxDto;
import edu.miu.cs590.repository.PaymentMethodRepo;
import edu.miu.cs590.service.PurchaseService;
import edu.miu.cs590.util.AppUtil;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private HttpServletRequest servletRequest;
    @Autowired
    private PaymentMethodRepo paymentMethodRepo;

    @Autowired
    private PaymentMethodDtoMapper mapper;

    @Autowired
    private TxServiceClient txClient;

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
        Transaction transaction = createTransaction(paymentMethod, request);

        // This might go to the payment method and do the payment verification and do the real purchase
        if(paymentMethod.getMethodType().equals(PaymentType.Value.CREDIT_CARD)) {
            System.out.println("Processing order with Credit card");
        } else if (paymentMethod.getMethodType().equals(PaymentType.Value.PAYPAL)) {
            System.out.println("Processing order with Paypal");
        }

        // Update status of the transaction after everything went well
        transaction = updateTransactionStatus(transaction);
        System.out.println("Transaction updated successfully " + transaction);
    }

    private Transaction createTransaction(PaymentMethodDto paymentMethod, PurchaseRequest request) {
        var authorization = servletRequest.getHeader("Authorization");
        PurchaseRequestTxDto mapper = Mappers.getMapper(PurchaseRequestTxDto.class);

        TxRequestDto txRequestDto = mapper.toTransaction(request);
        txRequestDto.setName("TX: #" + request.getOrderId());

        var txResponse = txClient.createTransaction(authorization, txRequestDto);
        return txResponse.getBody();
    }

    private Transaction updateTransactionStatus(Transaction transaction) {
        var authorization = servletRequest.getHeader("Authorization");
        Map<String, String> status = new HashMap<>();
        status.put("status", "SUCCESS");
        return txClient.updateStatus(authorization, transaction.getUuid(), status).getBody();
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

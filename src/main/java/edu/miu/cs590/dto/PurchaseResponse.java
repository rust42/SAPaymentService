package edu.miu.cs590.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Optional;


@Data
@Builder
public class PurchaseResponse {
    private PurchaseStatus purchaseStatus;
    private long orderId;
    private double paymentAmount;
    // set if status is failure
    private Optional<String> failureReason;

    private PaymentMethodDto paymentMethod;
}

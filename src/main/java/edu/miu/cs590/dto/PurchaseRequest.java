package edu.miu.cs590.dto;

import lombok.Data;

@Data
public class PurchaseRequest {
    private String orderId;
    private String amount;
    private String paymentMethodId;
}

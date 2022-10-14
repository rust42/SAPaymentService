package edu.miu.cs590.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class PurchaseRequest {
    @NotBlank
    private long orderId;

    @NotNull
    private double amount;

    @NotBlank
    private long paymentMethodId;
}

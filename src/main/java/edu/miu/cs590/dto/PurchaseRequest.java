package edu.miu.cs590.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class PurchaseRequest {
    @NotNull(message = "orderId cannot be null")
    private long orderId;

    @NotNull(message = "amount cannot be null")
    private double amount;

    @NotBlank(message = "paymentMethodId cannot be null")
    private long paymentMethodId;
}

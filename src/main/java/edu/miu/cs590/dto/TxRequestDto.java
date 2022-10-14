package edu.miu.cs590.dto;

import lombok.Data;

@Data
public class TxRequestDto {
    private String orderId;
    private double amount;
    private long paymentMethod;
    private String name;
}

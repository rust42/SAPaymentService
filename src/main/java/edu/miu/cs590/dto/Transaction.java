package edu.miu.cs590.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Transaction {
    private String uuid;
    private String orderId;
    private double amount;
    private long paymentMethod;
    private String name;
    private String status;
    private LocalDate createdDate;
}


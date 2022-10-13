package edu.miu.cs590.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PaypalAccountDto extends PaymentMethodDto {
    private LocalDate createdAt;
    private String accountId;
}

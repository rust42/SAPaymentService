package edu.miu.cs590.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreditCardDto extends PaymentMethodDto {
    private LocalDate createdAt;
    private String name;
    private String expirationYear;
    private String expirationMonth;
}

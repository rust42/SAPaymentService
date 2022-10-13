package edu.miu.cs590.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreditCardRequest {
    private String name;

    private String number;

    private String expirationYear;
    private String expirationMonth;

    private String cvc;
}

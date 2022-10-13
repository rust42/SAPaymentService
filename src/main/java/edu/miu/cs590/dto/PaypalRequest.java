package edu.miu.cs590.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class PaypalRequest {
    private String accountId;
}

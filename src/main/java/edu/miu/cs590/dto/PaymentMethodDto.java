package edu.miu.cs590.dto;
import lombok.Data;

@Data
public class PaymentMethodDto {
    private long id;
    private String username;
    private boolean isDefault;
    private String methodType;
}

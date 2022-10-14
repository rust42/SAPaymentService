package edu.miu.cs590.dto;
import edu.miu.cs590.entity.PaymentType;
import lombok.Data;

@Data
public class PaymentMethodDto {
    private long id;
    private String username;
    private boolean isDefault;
    private String methodType;
}

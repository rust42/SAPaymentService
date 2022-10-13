package edu.miu.cs590.entity;

import edu.miu.cs590.dto.PaymentMethodDto;
import edu.miu.cs590.mapper.EntityMapper;
import edu.miu.cs590.mapper.MappableEntity;
import edu.miu.cs590.mapper.PaymentMethodDtoMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

@Entity
@Getter
@Setter
@DiscriminatorValue(value = PaymentType.Value.PAYPAL)
@EntityListeners(AuditingEntityListener.class)
public class PaypalAccount extends PaymentMethod implements MappableEntity {
    private String accountId;

    @Override
    public PaymentMethodDto map(PaymentMethodDtoMapper mapper) {
        return mapper.map(this);
    }
}

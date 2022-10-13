package edu.miu.cs590.entity;

import edu.miu.cs590.dto.PaymentMethodDto;
import edu.miu.cs590.mapper.EntityMapper;
import edu.miu.cs590.mapper.MappableEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@Setter
@DiscriminatorValue(value = PaymentType.Value.CREDIT_CARD)
@EntityListeners(AuditingEntityListener.class)
public class CreditCard extends PaymentMethod implements MappableEntity {
    private String name;

    private String number;

    private String expirationYear;
    private String expirationMonth;

    private String cvc;

    @Override
    public PaymentMethodDto map(EntityMapper entityMapper) {
        return entityMapper.map(this);
    }
}

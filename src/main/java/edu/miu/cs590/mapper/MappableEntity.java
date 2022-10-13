package edu.miu.cs590.mapper;

import edu.miu.cs590.dto.PaymentMethodDto;

public interface MappableEntity {
    public PaymentMethodDto map(PaymentMethodDtoMapper entityMapper);
}

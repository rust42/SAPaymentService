package edu.miu.cs590.mapper;

import edu.miu.cs590.dto.PaypalAccountDto;
import edu.miu.cs590.entity.PaypalAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaypalAccountDtoMapper {
    @Mapping(target = "methodType", source = "method", defaultValue = "Paypal")

    PaypalAccountDto toDto(PaypalAccount account);
}

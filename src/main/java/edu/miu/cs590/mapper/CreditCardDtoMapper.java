package edu.miu.cs590.mapper;

import edu.miu.cs590.dto.CreditCardDto;
import edu.miu.cs590.entity.CreditCard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreditCardDtoMapper {


    @Mapping(source = "method", target = "methodType", defaultValue = "CreditCard")
    CreditCardDto toDto(CreditCard card);
}

package br.com.optimate.manager.dto;

import br.com.optimate.manager.domain.company.BusinessArea;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BusinessAreaMapper {

    @Mapping(target = "id", source = "id")
    BusinessArea toEntity(BusinessAreaDto businessAreaDto);

    @Mapping(target = "id", source = "id")
    BusinessAreaDto toDto(BusinessArea businessArea);

    List<BusinessArea> toEntityList(List<BusinessAreaDto> businessAreaDto);

    List<BusinessAreaDto> toDtoList(List<BusinessArea> businessArea);

}

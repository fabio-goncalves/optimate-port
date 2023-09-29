package br.com.optimate.manager.dto;

import br.com.optimate.manager.domain.port.PortFacility;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PortFacilityMapper {

    @Mapping(target = "id", source = "id")
    PortFacility toEntity(PortFacilityDto portFacilityDto);

    @Mapping(target = "id", source = "id")
    PortFacilityDto toDto(PortFacility portFacility);

    List<PortFacility> toEntityList(List<PortFacilityDto> portFacilityDtoList);

    List<PortFacilityDto> toDtoList(List<PortFacility> portFacilityList);
}

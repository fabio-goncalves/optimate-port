package br.com.optimate.manager.dto;

import br.com.optimate.manager.domain.port.MooringLocation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MooringLocationMapper {

    @Mapping(target = "id", source = "id")
    MooringLocation toEntity(MooringLocationDto mooringLocationDto);

    @Mapping(target = "id", source = "id")
    MooringLocationDto toDto(MooringLocation mooringLocation);

    List<MooringLocation> toEntityList(List<MooringLocationDto> mooringLocationDtoList);

    List<MooringLocationDto> toDtoList(List<MooringLocation> mooringLocations);

}

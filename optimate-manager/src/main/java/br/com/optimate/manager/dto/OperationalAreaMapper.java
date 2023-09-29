package br.com.optimate.manager.dto;

import br.com.optimate.manager.domain.port.OperationalArea;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OperationalAreaMapper {

    @Mapping(target = "id", source = "id")
    OperationalArea toEntity(OperationalAreaDto operationalAreaDto);

    @Mapping(target = "id", source = "id")
    OperationalAreaDto toDto(OperationalArea operationalArea);

    List<OperationalArea> toEntityList(List<OperationalAreaDto> operationalAreaDtoList);

    List<OperationalAreaDto> toDtoList(List<OperationalArea> operationalAreas);
}

package br.com.optimate.manager.dto;

import br.com.optimate.manager.domain.port.Port;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PortMapper {

    @Mapping(target = "id", source = "id")
    Port toEntity(PortDto portDto);

    @Mapping(target = "id", source = "id")
    PortDto toDto(Port port);

    List<Port> toEntityList(List<PortDto> portDtoList);

    List<PortDto> toDtoList(List<Port> portList);
}

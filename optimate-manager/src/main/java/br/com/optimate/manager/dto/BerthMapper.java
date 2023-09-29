package br.com.optimate.manager.dto;

import br.com.optimate.manager.domain.port.Berth;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BerthMapper {
    @Mapping(target = "id", source = "id")
    Berth toEntity(BerthDto berthDto);

    @Mapping(target = "id", source = "id")
    BerthDto toDto(Berth berth);

    List<Berth> toEntityList(List<BerthDto> berthDtoList);

    List<BerthDto> toDtoList(List<Berth> berthList);

}

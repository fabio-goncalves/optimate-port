package br.com.optimate.manager.dto;

import br.com.optimate.manager.domain.user.PersonalInformation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PersonalInformationMapper {

    @Mapping(target = "id", source = "id")
    PersonalInformation toEntity(PersonalInformationDto personalInformationDto);

    @Mapping(target = "id", source = "id")
    PersonalInformationDto toDto(PersonalInformation personalInformation);

}

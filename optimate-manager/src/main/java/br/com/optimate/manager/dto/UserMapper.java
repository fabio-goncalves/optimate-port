package br.com.optimate.manager.dto;

import br.com.optimate.manager.domain.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = PersonalInformationMapper.class)
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "personalInformation", source = "personalInformation")
    User toEntity(UserDto userDto);

    @Mapping(target = "id", source = "id")
    UserDto toDto(User user);

    @Mapping(target = "avatar", ignore = true)
    List<User> toEntityList(List<UserDto> userDtoList);

    @Mapping(target = "avatar", ignore = true)
    List<UserDto> toDtoList(List<User> userList);
}

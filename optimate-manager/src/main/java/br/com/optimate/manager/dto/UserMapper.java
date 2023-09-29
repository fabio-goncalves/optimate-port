package br.com.optimate.manager.dto;

import br.com.optimate.manager.domain.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mapping(target = "id", source = "id")
    User toEntity(UserDto userDto);

    @Mapping(target = "id", source = "id")
    UserDto toDto(User user);

    List<User> toEntityList(List<UserDto> userDtoList);

    List<UserDto> toDtoList(List<User> userList);
}

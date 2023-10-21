package br.com.optimate.manager.service;

import br.com.optimate.manager.domain.user.Avatar;
import br.com.optimate.manager.domain.user.User;
import br.com.optimate.manager.dto.UserDto;
import br.com.optimate.manager.dto.UserMapper;
import br.com.optimate.manager.repository.AvatarRepository;
import br.com.optimate.manager.repository.UserRepository;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserService implements AbstractService {

    @Inject
    UserRepository userRepository;
    @Inject
    UserMapper userMapper;
    @Inject
    AvatarService avatarService;
    @Inject
    AvatarRepository avatarRepository;
    private final JsonWebToken jwt;

    @Inject
    public UserService(JsonWebToken jwt) {
        this.jwt = jwt;
    }

    @Transactional
    public UserDto saveUser(UserDto userDto) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findUserByUsername(userDto.getUsername()));
        optionalUser.ifPresent(user -> {
            throw new WebApplicationException("Username já cadastrado!", Response.Status.BAD_REQUEST);
        });
        if(Optional.ofNullable(userDto.getAvatar()).isPresent())
            userDto.setAvatar(null);
        User user = userMapper.toEntity(userDto);
        userRepository.persist(user);
        return userMapper.toDto(user);
    }

    public List<UserDto> listAll() {
        return userMapper.toDtoList(userRepository.listAll());
    }

    public UserDto findUserByUsername(UserDto userDto) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findUserByUsername(userDto.getUsername()));
        User user = optionalUser.orElseThrow(() ->
                new WebApplicationException("Username não encontrado!", Response.Status.NOT_FOUND));
        return userMapper.toDto(user);
    }

    @Transactional
    public UserDto editUser(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByIdOptional(userDto.getId());
        User user = optionalUser.orElseThrow(() ->
                new WebApplicationException("Username não encontrado!", Response.Status.NOT_FOUND));
        userRepository.persist(userMapper.toEntity(userDto));
        return userMapper.toDto(user);
    }

    @Transactional
    public String deleteUser(long id) {
        Optional<User> optionalUser = userRepository.findByIdOptional(id);
        User user = optionalUser.orElseThrow(() -> new WebApplicationException("Usuário não encontrado", Response.Status.NOT_FOUND));
        userRepository.delete(user);
        return "Usuário excluído com sucesso!";
    }

    public UserDto getCurrentUser() {
        return userMapper.toDto(userRepository.findUserByUsername(jwt.getName()));
    }

    @Transactional
    public UserDto changePassword(String currentPassword, String newPassword) {
        Optional<UserDto> optionalUserDto = Optional.ofNullable(getCurrentUser());
        optionalUserDto.ifPresent(userDto -> {
            User user = userMapper.toEntity(userDto);
            if (!matches(user, currentPassword))
                throw new ClientErrorException("Current password does not match", Response.Status.CONFLICT);
            user.setPassword(BcryptUtil.bcryptHash(newPassword));
        });
        return optionalUserDto.orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }

    @Transactional
    public UserDto uploadAvatar(long id, String avatarImage) {
        Optional<User> optionalUser = userRepository.findByIdOptional(id);
        User user = optionalUser.orElseThrow(() ->
                new WebApplicationException("Username não encontrado!", Response.Status.NOT_FOUND));
        if(avatarImage.isEmpty())
            user.setAvatar(avatarService.createAvatarDefault(user));
        else {
            Avatar avatar = new Avatar(user);
            avatar.setAvatar220(avatarImage);
            avatarRepository.persist(avatar);
        }
        userRepository.persist(user);
        return userMapper.toDto(user);
    }

    public boolean matches(User user, String password) {
        return BcryptUtil.matches(password, user.getPassword());
    }
}

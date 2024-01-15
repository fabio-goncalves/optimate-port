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

    UserRepository userRepository;
    UserMapper userMapper;
    AvatarService avatarService;
    AvatarRepository avatarRepository;
    private final JsonWebToken jwt;

    @Inject
    public UserService(UserRepository userRepository, UserMapper userMapper, AvatarService avatarService, AvatarRepository avatarRepository, JsonWebToken jwt) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.avatarService = avatarService;
        this.avatarRepository = avatarRepository;
        this.jwt = jwt;
    }

    @Transactional
    public UserDto saveUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        checkUsername(user.getUsername());
        validateCompanyUser(user);
        if(Optional.ofNullable(userDto.getAvatar()).isPresent())
            userDto.setAvatar(null);
        userRepository.persist(user);
        return userMapper.toDto(user);
    }

    @Transactional
    public List<UserDto> listAll() {
        return userMapper.toDtoList(userRepository.listAll());
    }

    public UserDto findUserByUsername(UserDto userDto) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findUserByUsername(userDto.getUsername()));
        User user = optionalUser.orElseThrow(() ->
                new WebApplicationException(Response.Status.NOT_FOUND));
        return userMapper.toDto(user);
    }

    @Transactional
    public UserDto editUser(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByIdOptional(userDto.getId());
        User user = optionalUser.orElseThrow(() ->
                new WebApplicationException(Response.Status.NOT_FOUND));
        validateCompanyUser(user);
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

    public User getCurrentUser() {
        return userRepository.findUserByUsername(jwt.getName());
    }

    @Transactional
    public UserDto changePassword(String currentPassword, String newPassword) {
        Optional<User> optionalUser = Optional.ofNullable(getCurrentUser());
        optionalUser.ifPresent(user -> {
            if (!matches(user, currentPassword))
                throw new ClientErrorException("Current password does not match", Response.Status.CONFLICT);
            user.setPassword(BcryptUtil.bcryptHash(newPassword));
            userMapper.toDto(user);
        });
        return userMapper.toDto(optionalUser.orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND)));
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

    public boolean validateCompanyUser(User user) {
        User loggedUser = getCurrentUser();
        boolean isValidate = loggedUser.getRoles().contains("admin") && loggedUser.getCompanyList()
                .stream().anyMatch(company -> user.getCompanyList().contains(company));
        if(!isValidate)
            throw new WebApplicationException("Credenciais inválidas!", Response.Status.BAD_REQUEST);
        return true;
    }

    public void checkUsername(String username) {
        if(!userRepository.userIsNotAlreadyRegistry(username))
            throw new WebApplicationException("Username já cadastrado!", Response.Status.BAD_REQUEST);
    }
}

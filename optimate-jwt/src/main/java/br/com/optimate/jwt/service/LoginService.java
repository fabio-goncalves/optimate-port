package br.com.optimate.jwt.service;

import br.com.optimate.jwt.config.MessageResponse;
import br.com.optimate.jwt.config.TokenGenerator;
import br.com.optimate.jwt.domain.User;
import br.com.optimate.jwt.dto.LoginDto;
import br.com.optimate.jwt.repository.UserRepository;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@ApplicationScoped
public class LoginService implements AbstractService {


    UserRepository userRepository;

    @Inject
    LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public LoginDto authenticate(LoginDto loginDto) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.find("username", loginDto.getUsername()).firstResult());
        User userFound = optionalUser.orElseThrow(() ->
                new WebApplicationException(Response.ok(new MessageResponse("Usuário não econtrado!")).status(Response.Status.NOT_FOUND).build()));
        if (!matches(userFound, loginDto.getPassword()))
            throw new WebApplicationException(Response.ok(new MessageResponse("Credenciais inválidas!")).status(Response.Status.UNAUTHORIZED).build());
        loginDto.setToken(TokenGenerator.generateToken(userFound.getUsername(), userFound.getUsername(), userFound.getRoles()));
        loginDto.setRoles(userFound.getRoles());
        loginDto.setAvatar(userFound.getAvatar().getAvatar220());
        loginDto.setName(userFound.getUsername());
        loginDto.setUsername(userFound.getUsername());
        return loginDto;
    }

    public boolean matches(User user, String password) {
        return BcryptUtil.matches(password, user.getPassword());
    }

}

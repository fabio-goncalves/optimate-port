package br.com.optimate.jwt.service;

import br.com.optimate.jwt.config.TokenGenerator;
import br.com.optimate.jwt.domain.User;
import br.com.optimate.jwt.repository.UserRepository;
import br.com.optimate.jwt.service.dto.LoginDto;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.security.AuthenticationFailedException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@ApplicationScoped
public class LoginService implements AbstractService {

    @Inject
    UserRepository userRepository;

    public String authenticate(LoginDto loginDto) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.find("username", loginDto.getUsername()).firstResult());
        User userFound = optionalUser.orElseThrow(() -> new WebApplicationException("Username não encontrado!", Response.Status.NOT_FOUND));
        if (!matches(userFound, loginDto.getPassword()))
            throw new AuthenticationFailedException("Invalid credentials");
        return TokenGenerator.generateToken(userFound.getUsername(), userFound.getFirstName(), userFound.getRoles());
    }

    public boolean matches(User user, String password) {
        return BcryptUtil.matches(password, user.getPassword());
    }

}

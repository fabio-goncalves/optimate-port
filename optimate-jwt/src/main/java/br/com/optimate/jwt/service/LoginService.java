package br.com.optimate.jwt.service;

import br.com.optimate.jwt.config.MessageResponse;
import br.com.optimate.jwt.config.TokenGenerator;
import br.com.optimate.jwt.domain.User;
import br.com.optimate.jwt.dto.LoginDto;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class LoginService implements AbstractService {

    public LoginDto authenticate(LoginDto loginDto, User user) {
        if (!matches(user.getPassword(), loginDto.getPassword()))
            throw new WebApplicationException(Response.ok(new MessageResponse("Credenciais inválidas!")).status(Response.Status.UNAUTHORIZED).build());
        loginDto.setToken(TokenGenerator.generateToken(user.getUsername(), user.getUsername(), user.getRoles()));
        loginDto.setRoles(user.getRoles());
        loginDto.setAvatar(user.getAvatar().getAvatar220());
        loginDto.setName(user.getUsername());
        loginDto.setUsername(user.getUsername());
        return loginDto;
    }

    public boolean matches(String passwd, String password) {
        return BcryptUtil.matches(password, passwd);
    }

}

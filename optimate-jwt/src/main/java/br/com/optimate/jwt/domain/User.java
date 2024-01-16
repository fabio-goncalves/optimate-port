package br.com.optimate.jwt.domain;

import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@UserDefinition
@Data
public class User {


    @Username
    private String username;
    @Password
    private String password;
    @Roles
    private List<String> roles;
    private Avatar avatar;

}

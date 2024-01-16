package br.com.optimate.jwt.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class LoginDto {
    @NotNull
    private String username;
    @NotNull
    private String password;
    private String token;
    private List<String> roles;
    private String avatar;
    private String name;

}

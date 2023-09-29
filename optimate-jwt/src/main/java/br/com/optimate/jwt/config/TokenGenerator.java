package br.com.optimate.jwt.config;


import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.jwt.Claims;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;

public class TokenGenerator {

    public static String generateToken(String username, String name, List<String> roles) {
        return Jwt.upn(username)
                .issuer("optimate-jwt")
                .subject("optimate")
                .claim(Claims.upn.name(), username)
                .claim(Claims.preferred_username.name(), name)
                .groups(new HashSet<>(roles))
                .audience("optimate-jwt")
                .expiresIn(Duration.ofHours(8))
                .sign();

    }
}

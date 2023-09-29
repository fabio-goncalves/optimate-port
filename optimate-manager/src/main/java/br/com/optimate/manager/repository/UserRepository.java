package br.com.optimate.manager.repository;

import br.com.optimate.manager.domain.user.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public User findUserByUsername(String username) {
        return find("username", username).firstResult();
    }
}

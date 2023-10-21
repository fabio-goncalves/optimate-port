package br.com.optimate.manager.repository;

import br.com.optimate.manager.domain.user.Avatar;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AvatarRepository implements PanacheRepository<Avatar> {
}

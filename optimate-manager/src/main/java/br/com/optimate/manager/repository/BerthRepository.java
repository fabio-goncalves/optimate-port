package br.com.optimate.manager.repository;

import br.com.optimate.manager.domain.port.Berth;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BerthRepository implements PanacheRepository<Berth> {

    public Berth findBerthByacronymBerth(String acronymBerth) {
        return find("acronymBerth", acronymBerth).firstResult();
    }
    public Berth findBerthByName(String name) {
        return find("name", name).firstResult();
    }
}

package br.com.optimate.manager.repository;

import br.com.optimate.manager.domain.port.Port;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PortRepository implements PanacheRepository<Port> {

    public Port findPortByName(String name) {
        return find("name", name).firstResult();
    }
}

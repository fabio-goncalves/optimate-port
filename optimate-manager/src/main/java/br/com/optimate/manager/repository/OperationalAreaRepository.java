package br.com.optimate.manager.repository;

import br.com.optimate.manager.domain.port.OperationalArea;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OperationalAreaRepository implements PanacheRepository<OperationalArea> {

    public OperationalArea findMooringLocationByAcronym(String acronym) {
        return find("acronym", acronym).firstResult();
    }
}

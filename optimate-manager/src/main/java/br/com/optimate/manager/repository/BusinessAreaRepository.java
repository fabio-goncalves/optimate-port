package br.com.optimate.manager.repository;

import br.com.optimate.manager.domain.company.BusinessArea;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BusinessAreaRepository implements PanacheRepository<BusinessArea> {

    public BusinessArea findByName(String name) { return find("name", name).firstResult(); }
}

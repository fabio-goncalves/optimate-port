package br.com.optimate.manager.repository;

import br.com.optimate.manager.domain.port.MooringLocation;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MooringLocationRepository implements PanacheRepository<MooringLocation> {

    public MooringLocation findMooringLocationByAcronym(String acronymMooring) {
        return find("acronymMooring", acronymMooring).firstResult();
    }
}

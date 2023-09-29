package br.com.optimate.manager.repository;

import br.com.optimate.manager.domain.port.PortFacility;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PortFacilityRepository implements PanacheRepository<PortFacility> {
    public PortFacility findPortFacilityByName(String name) {
        return find("name", name).firstResult();
    }

    public PortFacility findPortFacilityByAcronymPort(String acronymPort) {
        return find("acronymPort", acronymPort).firstResult();
    }
}

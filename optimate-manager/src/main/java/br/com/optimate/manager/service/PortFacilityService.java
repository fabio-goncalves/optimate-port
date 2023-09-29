package br.com.optimate.manager.service;

import br.com.optimate.manager.domain.port.PortFacility;
import br.com.optimate.manager.repository.PortFacilityRepository;
import br.com.optimate.manager.dto.PortFacilityDto;
import br.com.optimate.manager.dto.PortFacilityMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PortFacilityService implements AbstractService {

    @Inject
    PortFacilityRepository portFacilityRepository;
    @Inject
    PortFacilityMapper portFacilityMapper;

    @Transactional
    public PortFacilityDto savePortFacility(PortFacilityDto portFacilityDto) {
        Optional<PortFacility> optionalPortFacility = Optional.ofNullable(portFacilityRepository.findPortFacilityByAcronymPort(portFacilityDto.getAcronymPort()));
        optionalPortFacility.ifPresent(portFacility -> {
            throw new WebApplicationException("Área de negócio já cadastrada!", Response.Status.FOUND);
        });
        portFacilityRepository.persist(portFacilityMapper.toEntity(portFacilityDto));
        return portFacilityDto;
    }

    public List<PortFacilityDto> listAll() {
        return portFacilityMapper.toDtoList(portFacilityRepository.listAll());
    }

    public PortFacilityDto findPortFacilityByName(PortFacilityDto portFacilityDto) {
        Optional<PortFacility> optionalPortFacility = portFacilityRepository.findByIdOptional(portFacilityDto.getId());
        PortFacility portFacility = optionalPortFacility.orElseThrow(() -> new WebApplicationException("Unidade Poruária não encontrada!", Response.Status.NOT_FOUND));
        return portFacilityMapper.toDto(portFacility);
    }

    @Transactional
    public PortFacilityDto editPortFacility(PortFacilityDto portFacilityDto) {
        Optional<PortFacility> optionalPortFacility = portFacilityRepository.findByIdOptional(portFacilityDto.getId());
        PortFacility portFacility = optionalPortFacility.orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
        portFacilityRepository.persist(portFacility);
        return portFacilityDto;
    }
}

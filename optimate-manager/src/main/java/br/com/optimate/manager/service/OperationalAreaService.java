package br.com.optimate.manager.service;

import br.com.optimate.manager.domain.port.OperationalArea;
import br.com.optimate.manager.repository.OperationalAreaRepository;
import br.com.optimate.manager.dto.OperationalAreaDto;
import br.com.optimate.manager.dto.OperationalAreaMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class OperationalAreaService implements AbstractService {

    OperationalAreaRepository operationalAreaRepository;
    OperationalAreaMapper operationalAreaMapper;

    @Inject
    public OperationalAreaService(OperationalAreaRepository operationalAreaRepository, OperationalAreaMapper operationalAreaMapper) {
        this.operationalAreaRepository = operationalAreaRepository;
        this.operationalAreaMapper = operationalAreaMapper;
    }
    @Transactional
    public OperationalAreaDto saveOperationalArea(OperationalAreaDto operationalAreaDto) {
        Optional<OperationalArea> operationalAreaOptional = Optional.ofNullable(operationalAreaRepository.findMooringLocationByAcronym(operationalAreaDto.getAcronym()));
        operationalAreaOptional.ifPresent(operationalArea -> {
            throw new WebApplicationException("Área de negócio já cadastrada!", Response.Status.FOUND);
        });
        operationalAreaRepository.persist(operationalAreaMapper.toEntity(operationalAreaDto));
        return operationalAreaDto;
    }

    public List<OperationalAreaDto> listAll() {
        return operationalAreaMapper.toDtoList(operationalAreaRepository.listAll());
    }

    public OperationalAreaDto findMooringLocationByAcronym(OperationalAreaDto operationalAreaDto) {
        Optional<OperationalArea> optionalOperationalArea = Optional.ofNullable(operationalAreaRepository
                .findMooringLocationByAcronym(operationalAreaDto.getAcronym()));
        OperationalArea operationalArea = optionalOperationalArea.orElseThrow(() -> new WebApplicationException("Área operacional não encontrada!"));
        return operationalAreaMapper.toDto(operationalArea);
    }

    @Transactional
    public OperationalAreaDto editMooringLocation(OperationalAreaDto operationalAreaDto) {
        Optional<OperationalArea> optionalOperationalArea = operationalAreaRepository.findByIdOptional(operationalAreaDto.getId());
        OperationalArea operationalArea = optionalOperationalArea.orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
        operationalAreaRepository.persist(operationalArea);
        return operationalAreaMapper.toDto(operationalArea);
    }
}
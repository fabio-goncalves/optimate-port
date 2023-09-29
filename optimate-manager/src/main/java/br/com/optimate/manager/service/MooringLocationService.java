package br.com.optimate.manager.service;

import br.com.optimate.manager.domain.port.Berth;
import br.com.optimate.manager.domain.port.MooringLocation;
import br.com.optimate.manager.repository.MooringLocationRepository;
import br.com.optimate.manager.dto.MooringLocationDto;
import br.com.optimate.manager.dto.MooringLocationMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MooringLocationService implements AbstractService {

    @Inject
    MooringLocationRepository mooringLocationRepository;
    @Inject
    MooringLocationMapper mooringLocationMapper;

    @Transactional
    public MooringLocationDto saveMooringLocation(MooringLocationDto mooringLocationDto) {
        Optional<MooringLocation> mooringLocationOptional = Optional.ofNullable(mooringLocationRepository.findMooringLocationByAcronym(mooringLocationDto.getAcronymMooring()));
        mooringLocationOptional.ifPresent(mooringLocation -> {
            throw new WebApplicationException("Local de atracação já existente", Response.Status.FOUND);
        });
        mooringLocationRepository.persist(mooringLocationMapper.toEntity(mooringLocationDto));
        return mooringLocationDto;
    }

    public List<MooringLocationDto> listAll() {
        return mooringLocationMapper.toDtoList(mooringLocationRepository.listAll());
    }

    public MooringLocationDto findMooringLocationByAcronym(MooringLocationDto mooringLocationDto) {
        Optional<MooringLocation> optionalMooringLocation = Optional.ofNullable(mooringLocationRepository
                .findMooringLocationByAcronym(mooringLocationDto.getAcronymMooring()));
        MooringLocation mooringLocation = optionalMooringLocation.orElseThrow(() -> new WebApplicationException("Local de atracação não encontrado!"));
        return mooringLocationMapper.toDto(mooringLocation);
    }

    @Transactional
    public MooringLocationDto editMooringLocation(MooringLocationDto mooringLocationDto) {
        Optional<MooringLocation> optionalMooringLocation = mooringLocationRepository.findByIdOptional(mooringLocationDto.getId());
        MooringLocation mooringLocation = optionalMooringLocation.orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
        mooringLocationRepository.persist(mooringLocation);
        return mooringLocationDto;
    }

    @Transactional
    public MooringLocationDto addBerth (MooringLocationDto mooringLocationDto, Berth berth) {
        Optional<MooringLocation> optionalMooringLocation = mooringLocationRepository.findByIdOptional(mooringLocationDto.getId());
        MooringLocation mooringLocation = optionalMooringLocation.orElseThrow(() ->
                new WebApplicationException("Local de atracação não encontrado!", Response.Status.NOT_FOUND));
        mooringLocation.addBerth(berth);
        return mooringLocationMapper.toDto(mooringLocation);
    }
}

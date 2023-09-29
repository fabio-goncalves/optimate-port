package br.com.optimate.manager.service;

import br.com.optimate.manager.domain.port.Berth;
import br.com.optimate.manager.repository.BerthRepository;
import br.com.optimate.manager.dto.BerthDto;
import br.com.optimate.manager.dto.BerthMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BerthService implements AbstractService {

    @Inject
    BerthRepository berthRepository;
    @Inject
    BerthMapper berthMapper;

    @Transactional
    public BerthDto saveBerth(BerthDto berthDTO) {
        Optional<Berth> berthOptional = Optional.ofNullable(berthRepository.findBerthByacronymBerth(berthDTO.getAcronymBerth()));
        berthOptional.ifPresent(berth -> {
            throw new WebApplicationException("Área de negócio já cadastrada!", Response.Status.FOUND);
        });
        berthRepository.persist(berthMapper.toEntity(berthDTO));
        return berthDTO;
    }

    public List<BerthDto> listAll() {
        return berthMapper.toDtoList(berthRepository.listAll());
    }

    public BerthDto findBerthByName(BerthDto berthDto) {
        Optional<Berth> optionalBerth = Optional.ofNullable(berthRepository.findBerthByName(berthDto.getName()));
        Berth berth = optionalBerth.orElseThrow(() -> new WebApplicationException("Berço nao encontrado!", Response.Status.NOT_FOUND));
        return berthMapper.toDto(berth);
    }

    @Transactional
    public BerthDto editBerth(BerthDto berthDto) {
        Optional<Berth> berthOptional = berthRepository.findByIdOptional(berthDto.getId());
        Berth berth = berthOptional.orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
        berthRepository.persist(berthMapper.toEntity(berthDto));
        return berthMapper.toDto(berth);
    }

}

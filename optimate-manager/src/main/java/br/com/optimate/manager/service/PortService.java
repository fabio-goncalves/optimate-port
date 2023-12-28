package br.com.optimate.manager.service;

import br.com.optimate.manager.domain.port.Port;
import br.com.optimate.manager.repository.PortRepository;
import br.com.optimate.manager.dto.PortDto;
import br.com.optimate.manager.dto.PortMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PortService implements AbstractService {

    PortRepository portRepository;
    PortMapper portMapper;

    @Inject
    public PortService(PortRepository portRepository, PortMapper portMapper) {
        this.portRepository = portRepository;
        this.portMapper = portMapper;
    }

    @Transactional
    public PortDto savePort(PortDto portDto) {
    Optional<Port> portOptional = Optional.ofNullable(portRepository.findPortByName(portDto.getName()));
    portOptional.ifPresent(port -> {
        throw new WebApplicationException("Porto já cadastrado!", Response.Status.FOUND);
    });
    portRepository.persist(portMapper.toEntity(portDto));
    return portDto;
    }

    public List<PortDto> listAll() {
        return portMapper.toDtoList(portRepository.listAll());
    }

    public PortDto findPortByName(PortDto portDto) {
        Optional<Port> optionalPort = Optional.ofNullable(portRepository.findPortByName(portDto.getName()));
        Port port = optionalPort.orElseThrow(() -> new WebApplicationException("Porto não encontrado!"));
        return portMapper.toDto(port);
    }

    @Transactional
    public PortDto editPort(PortDto portDto) {
        Optional<Port> portOptional = portRepository.findByIdOptional(portDto.getId());
        Port port = portOptional.orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
        portRepository.persist(port);
        return portDto;
    }
}

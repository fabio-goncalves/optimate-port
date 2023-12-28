package br.com.optimate.manager.service;

import br.com.optimate.manager.domain.company.BusinessArea;
import br.com.optimate.manager.repository.BusinessAreaRepository;
import br.com.optimate.manager.dto.BusinessAreaDto;
import br.com.optimate.manager.dto.BusinessAreaMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BusinessAreaService implements AbstractService {

    BusinessAreaRepository businessAreaRepository;
    BusinessAreaMapper businessAreaMapper;

    @Inject
    public BusinessAreaService(BusinessAreaRepository businessAreaRepository, BusinessAreaMapper businessAreaMapper) {
        this.businessAreaRepository = businessAreaRepository;
        this.businessAreaMapper = businessAreaMapper;
    }
    @Transactional
    public BusinessAreaDto saveBusinessArea(BusinessAreaDto businessAreaDTO) {
        Optional.ofNullable(businessAreaRepository.findByName(businessAreaDTO.getName()))
                .ifPresent(businessArea -> {
                    throw new WebApplicationException("Área de negócio já cadastrada!", Response.Status.FOUND);
                });
        businessAreaRepository.persist(businessAreaMapper.toEntity(businessAreaDTO));
        return businessAreaDTO;
    }

    public List<BusinessAreaDto> listBusinessArea() {
        return businessAreaMapper.toDtoList(businessAreaRepository.listAll());
    }

    @Transactional
    public BusinessAreaDto editBusinessArea(BusinessAreaDto businessAreaDTO) {
        Optional<BusinessArea> businessAreaOptional = businessAreaRepository.findByIdOptional(businessAreaDTO.getId());
        BusinessArea businessArea = businessAreaOptional.orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
        businessAreaRepository.persist(businessAreaMapper.toEntity(businessAreaDTO));
        return businessAreaMapper.toDto(businessArea);
    }
}

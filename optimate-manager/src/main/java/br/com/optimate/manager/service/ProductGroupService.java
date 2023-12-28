package br.com.optimate.manager.service;

import br.com.optimate.manager.domain.product.ProductGroup;
import br.com.optimate.manager.repository.ProductGroupRepository;
import br.com.optimate.manager.dto.ProductGroupDto;
import br.com.optimate.manager.dto.ProductGroupMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProductGroupService implements AbstractService {

    ProductGroupRepository productGroupRepository;
    ProductGroupMapper productGroupMapper;

    @Inject
    public ProductGroupService(ProductGroupRepository productGroupRepository, ProductGroupMapper productGroupMapper) {
        this.productGroupRepository = productGroupRepository;
        this.productGroupMapper = productGroupMapper;
    }

    @Transactional
    public ProductGroupDto saveProductGroup(ProductGroupDto productGroupDto) {
        Optional<ProductGroup> optionalProductGroup = productGroupRepository.findByIdOptional(productGroupDto.getId());
        optionalProductGroup.ifPresent(productGroup -> {
            throw new WebApplicationException("Grupo de Produto já existente", Response.Status.FOUND);
        });
        productGroupRepository.persist(productGroupMapper.toEntity(productGroupDto));
        return productGroupDto;
    }

    public List<ProductGroupDto> listAll(){
        return productGroupMapper.toDtoList(productGroupRepository.listAll());
    }

    public ProductGroupDto findListProductByAcronym(ProductGroupDto productGroupDto) {
        Optional<ProductGroup> optionalProductGroup = productGroupRepository.findByIdOptional(productGroupDto.getId());
        ProductGroup productGroup = optionalProductGroup.orElseThrow(() -> new WebApplicationException("Grupo de produto nao encontrado!"));
        return productGroupMapper.toDto(productGroup);
    }

    @Transactional
    public ProductGroupDto editProductGroup(ProductGroupDto productGroupDto) {
        Optional<ProductGroup> optionalProductGroup = productGroupRepository.findByIdOptional(productGroupDto.getId());
        ProductGroup productGroup = optionalProductGroup.orElseThrow(() -> new WebApplicationException("Grupo de produto não encontrado!", Response.Status.NOT_FOUND));
        productGroupRepository.persist(productGroup);
        return productGroupDto;
    }
}

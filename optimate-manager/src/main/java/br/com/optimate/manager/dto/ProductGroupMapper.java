package br.com.optimate.manager.dto;

import br.com.optimate.manager.domain.product.ProductGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductGroupMapper {

    @Mapping(target = "id", source = "id")
    ProductGroup toEntity(ProductGroupDto productGroupDto);

    @Mapping(target = "id", source = "id")
    ProductGroupDto toDto(ProductGroup productGroup);

    List<ProductGroup> toEntityList(List<ProductGroupDto> productGroupDtoList);

    List<ProductGroupDto> toDtoList(List<ProductGroup> productGroupList);
}

package br.com.optimate.manager.dto;

import br.com.optimate.manager.domain.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    @Mapping(target = "id", source = "id")
    Product toEntity(ProductDto productDto);

    @Mapping(target = "id", source = "id")
    ProductDto toDto(Product product);

    List<Product> toEntityList(List<ProductDto> productDtoList);

    List<ProductDto> toDtoList(List<Product> productList);
}

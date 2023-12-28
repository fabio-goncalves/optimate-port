package br.com.optimate.manager.dto;

import br.com.optimate.manager.domain.product.ProductGroup;
import lombok.Data;

@Data
public class ProductDto {

    private Long id;
    private String acronym;
    private String description;
    private ProductGroup productGroup;
    private Boolean isActive;

}

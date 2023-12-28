package br.com.optimate.manager.dto;

import br.com.optimate.manager.domain.product.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductGroupDto {

    private Long id;
    private String acronym;
    private String description;
    private Double tolerance;
    private List<Product> productList;

}

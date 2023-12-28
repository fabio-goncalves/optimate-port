package br.com.optimate.manager.domain.product;

import br.com.optimate.manager.domain.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity
@Data
@SequenceGenerator(initialValue = 10, name = "seq_commodity_group", sequenceName = "seq_commodity_group")
public class ProductGroup implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_commodity_group")
    private Long id;
    @NotNull
    @Size(min = 1, max = 10)
    private String acronym;
    @Size(max = 150)
    private String description;
    private Double tolerance;
    @OneToMany(mappedBy = "productGroup")
    private List<Product> productList;

    public ProductGroup() {
    }

    public ProductGroup(Long id, String acronym, String description, Double tolerance, List<Product> productList) {
        this.id = id;
        this.acronym = acronym;
        this.description = description;
        this.tolerance = tolerance;
        this.productList = productList;
    }

    @Override
    public Long getId() {
        return id;
    }

}

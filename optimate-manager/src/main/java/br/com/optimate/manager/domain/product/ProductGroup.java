package br.com.optimate.manager.domain.product;

import br.com.optimate.manager.domain.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTolerance() {
        return tolerance;
    }

    public void setTolerance(Double tolerance) {
        this.tolerance = tolerance;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}

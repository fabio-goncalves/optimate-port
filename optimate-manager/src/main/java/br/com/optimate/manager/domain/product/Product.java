package br.com.optimate.manager.domain.product;

import br.com.optimate.manager.domain.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@SequenceGenerator(initialValue = 10, name = "seq_commodity", sequenceName = "seq_commodity")
public class Product implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_commodity")
    private Long id;
    @NotNull
    @Size(min = 1, max = 10)
    private String acronym;
    @Size(max = 150)
    private String description;
    @ManyToOne
    @JoinColumn(name = "productGroup_id")
    private ProductGroup productGroup;
    @NotNull
    @Column(nullable = false)
    private Boolean isActive;

    public Product() {
    }

    public Product(Long id, String acronym, String description, ProductGroup productGroup, Boolean isActive) {
        this.id = id;
        this.acronym = acronym;
        this.description = description;
        this.productGroup = productGroup;
        this.isActive = isActive;
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

    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}

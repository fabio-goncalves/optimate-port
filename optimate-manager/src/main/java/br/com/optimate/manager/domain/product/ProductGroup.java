package br.com.optimate.manager.domain.product;

import br.com.optimate.manager.domain.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "product_group")
@Data
@SequenceGenerator(initialValue = 120, name = "seq_commodity_group", sequenceName = "seq_commodity_group")
public class ProductGroup implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_commodity_group")
    private Long id;
    @NotNull
    @Size(min = 1, max = 10)
    @Column(unique = true)
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

    @Override
    public String toString() {
        return "ProductGroup{" +
                "id=" + id +
                ", acronym='" + acronym + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductGroup that = (ProductGroup) o;
        return Objects.equals(acronym, that.acronym);
    }

    @Override
    public int hashCode() {
        return Objects.hash(acronym);
    }
}

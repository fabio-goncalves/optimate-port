package br.com.optimate.manager.domain.product;

import br.com.optimate.manager.domain.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Objects;

@Entity
@Data
@SequenceGenerator(initialValue = 10, name = "seq_commodity", sequenceName = "seq_commodity")
public class Product implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_commodity")
    private Long id;
    @NotNull
    @Size(min = 1, max = 10)
    @Column(unique = true)
    private String acronym;
    @Size(max = 150)
    private String description;
    @ManyToOne
    @JoinColumn(name = "productGroup_id")
    private ProductGroup productGroup;
    @NotNull
    @Column(name = "is_active", nullable = false)
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
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", acronym='" + acronym + '\'' +
                ", description='" + description + '\'' +
                ", isActive=" + isActive +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(acronym, product.acronym);
    }

    @Override
    public int hashCode() {
        return Objects.hash(acronym);
    }
}

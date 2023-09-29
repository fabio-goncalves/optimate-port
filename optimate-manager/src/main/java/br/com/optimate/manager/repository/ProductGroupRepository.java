package br.com.optimate.manager.repository;

import br.com.optimate.manager.domain.product.ProductGroup;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ProductGroupRepository implements PanacheRepository<ProductGroup> {

    public ProductGroup findProductByAcronym(String acronym) {
        return find("acronym", acronym).firstResult();
    }

    public List<ProductGroup> findListProductByAcronym(String acronym) {
        return find("acronym", acronym).stream().toList();
    }
}

package br.com.optimate.manager.repository;

import br.com.optimate.manager.domain.product.Product;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {

    public Product findProductByAcronym(String name) {
        return find("acronym", name).firstResult();
    }
}

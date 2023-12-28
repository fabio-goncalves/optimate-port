package br.com.optimate.manager.service;

import br.com.optimate.manager.domain.product.Product;
import br.com.optimate.manager.repository.ProductRepository;
import br.com.optimate.manager.dto.ProductDto;
import br.com.optimate.manager.dto.ProductMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProductService implements AbstractService {

    ProductRepository productRepository;
    ProductMapper productMapper;

    @Inject
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Transactional
    public ProductDto saveProduct(ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findByIdOptional(productDto.getId());
        optionalProduct.ifPresent(product -> {
            throw new WebApplicationException("Produto já cadastrado!", Response.Status.FOUND);
        });
        productRepository.persist(productMapper.toEntity(productDto));
        return productDto;
    }

    public List<ProductDto> listAll() {
        return productMapper.toDtoList(productRepository.listAll());
    }

    public ProductDto findProductByAcronym(ProductDto productDto) {
        return productMapper.toDto(productRepository.findProductByAcronym(productDto.getAcronym()));
    }

    @Transactional
    public ProductDto editProduct(ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findByIdOptional(productDto.getId());
        Product product = optionalProduct.orElseThrow(() -> new WebApplicationException("Produto não encontrado!", Response.Status.NOT_FOUND));
        productRepository.persist(product);
        return productDto;
    }
}

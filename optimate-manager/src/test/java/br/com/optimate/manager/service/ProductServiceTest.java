package br.com.optimate.manager.service;

import br.com.optimate.manager.domain.product.Product;
import br.com.optimate.manager.repository.ProductRepository;
import br.com.optimate.manager.dto.ProductMapper;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

@QuarkusTest
class ProductServiceTest {

    @Inject
    ProductService productService;
    @Inject
    ProductMapper productMapper;
    @InjectMock
    ProductRepository productRepository;
    Product product;
    List<Product> productList;

    @BeforeEach
    void init() {
        this.product = new Product(1L, "AFAF", "Soja", null, true);
        this.productList = List.of(product);
    }

    @Test
    void saveProduct() {
        Mockito.when(productRepository.findProductByAcronym(Mockito.anyString())).thenReturn(product);
        Assertions.assertEquals(product.getAcronym(), productService.saveProduct(productMapper.toDto(product)).getAcronym());
    }

    @Test
    void listAll() {
        Mockito.when(productRepository.listAll()).thenReturn(productList);
        Assertions.assertEquals(1, productService.listAll().size());
    }

    @Test
    void findProductByAcronym() {
        Mockito.when(productRepository.findProductByAcronym(Mockito.anyString())).thenReturn(product);
        Assertions.assertEquals(product.getAcronym(), productService.findProductByAcronym(productMapper.toDto(product)).getAcronym());
    }

    @Test
    void editProduct() {
        product.setAcronym("CCC");
        Mockito.when(productRepository.findByIdOptional(Mockito.anyLong())).thenReturn(Optional.ofNullable(product));
        Assertions.assertEquals(product.getAcronym(), productService.editProduct(productMapper.toDto(product)).getAcronym());
        Mockito.verify(productRepository).persist(product);
    }
}
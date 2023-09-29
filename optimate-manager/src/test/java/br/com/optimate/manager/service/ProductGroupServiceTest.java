package br.com.optimate.manager.service;

import br.com.optimate.manager.domain.product.ProductGroup;
import br.com.optimate.manager.repository.ProductGroupRepository;
import br.com.optimate.manager.dto.ProductGroupMapper;
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
class ProductGroupServiceTest {

    @Inject
    ProductGroupService productGroupService;
    @Inject
    ProductGroupMapper productGroupMapper;
    @InjectMock
    ProductGroupRepository productGroupRepository;
    ProductGroup productGroup;
    List<ProductGroup> productGroupList;

    @BeforeEach
    void init() {
        this.productGroup = new ProductGroup(1L, "AAAA", "Material de limpeza", 2.0, null);
        this.productGroupList = List.of(productGroup);
    }

    @Test
    void saveProductGroup() {
        Mockito.when(productGroupRepository.findProductByAcronym(Mockito.anyString())).thenReturn(productGroup);
        Assertions.assertEquals(productGroup.getAcronym(), productGroupService.saveProductGroup(productGroupMapper.toDto(productGroup)).getAcronym());
    }

    @Test
    void listAll() {
        Mockito.when(productGroupRepository.listAll()).thenReturn(productGroupList);
        Assertions.assertEquals(1, productGroupService.listAll().size());
    }

    @Test
    void findListProductByAcronym() {
        Mockito.when(productGroupRepository.findByIdOptional(Mockito.anyLong())).thenReturn(Optional.ofNullable(productGroup));
        Assertions.assertEquals(productGroup.getAcronym(), productGroupService.findListProductByAcronym(productGroupMapper.toDto(productGroup)).getAcronym());
    }

    @Test
    void editProductGroup() {
        productGroup.setAcronym("BBBB");
        Mockito.when(productGroupRepository.findByIdOptional(Mockito.anyLong())).thenReturn(Optional.ofNullable(productGroup));
        Assertions.assertEquals("BBBB", productGroupService.editProductGroup(productGroupMapper.toDto(productGroup)).getAcronym());
    }
}
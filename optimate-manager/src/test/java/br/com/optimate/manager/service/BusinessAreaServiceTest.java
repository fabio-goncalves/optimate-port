package br.com.optimate.manager.service;

import br.com.optimate.manager.domain.company.BusinessArea;
import br.com.optimate.manager.dto.BusinessAreaDto;
import br.com.optimate.manager.repository.BusinessAreaRepository;
import br.com.optimate.manager.dto.BusinessAreaMapper;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

@QuarkusTest
class BusinessAreaServiceTest {

    @InjectMock
    BusinessAreaRepository businessAreaRepositoryMock;
    @Inject
    BusinessAreaService businessAreaService;
    @Inject
    BusinessAreaMapper businessAreaMapper;
    BusinessArea businessArea;
    BusinessAreaDto businessAreaDto;
    List<BusinessArea> businessAreaList;

    @BeforeEach
    void init() {
        this.businessArea = new BusinessArea(1L, "Agente", "Empresa Responsável pela operação portuária");
        BusinessArea businessArea1 = new BusinessArea(2L, "Armador", "Dono da embarcação");
        this.businessAreaList = List.of(businessArea, businessArea1);
        this.businessAreaDto = businessAreaMapper.toDto(businessArea);
    }

    @Test
    void saveBusinessAreaWithExistsBusinessArea() {
        Mockito.when(businessAreaRepositoryMock.findByName(Mockito.anyString())).thenReturn(businessArea);
        Assertions.assertThrows(WebApplicationException.class, () -> businessAreaService.saveBusinessArea(businessAreaDto));
    }

    @Test
    void saveBusinessArea() {
        Mockito.when(businessAreaRepositoryMock.findByName(Mockito.anyString())).thenReturn(null);
        Assertions.assertEquals(businessAreaMapper.toDto(businessArea).getId(), businessAreaService.saveBusinessArea(businessAreaDto).getId());
    }

    @Test
    void listBusinessArea() {
        Mockito.when(businessAreaRepositoryMock.listAll()).thenReturn(businessAreaList);
        Assertions.assertSame(businessAreaList.get(0).getId(), businessAreaService.listBusinessArea().get(0).getId());
        Assertions.assertEquals(2, businessAreaService.listBusinessArea().size());
    }

    @Test
    void editBusinessArea() {
        businessArea.setName("Agente alterado");
        Mockito.when(businessAreaRepositoryMock.findByIdOptional(Mockito.anyLong())).thenReturn(Optional.of(businessArea));
        Assertions.assertEquals("Agente alterado", businessAreaService.editBusinessArea(businessAreaDto).getName());
    }

    @Test
    void editBusinessAreaWithNoExistsBusinessArea() {
        businessArea.setName("Agente alterado");
        Mockito.when(businessAreaRepositoryMock.findById(Mockito.anyLong())).thenReturn(null);
        Assertions.assertThrows(WebApplicationException.class, () -> businessAreaService.editBusinessArea(businessAreaDto));
    }
}
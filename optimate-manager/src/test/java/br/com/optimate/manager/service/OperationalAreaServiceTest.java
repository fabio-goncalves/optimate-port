package br.com.optimate.manager.service;

import br.com.optimate.manager.domain.port.OperationalArea;
import br.com.optimate.manager.dto.OperationalAreaDto;
import br.com.optimate.manager.repository.OperationalAreaRepository;
import br.com.optimate.manager.dto.OperationalAreaMapper;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class OperationalAreaServiceTest {

    @Inject
    OperationalAreaService operationalAreaService;
    @Inject
    OperationalAreaMapper operationalAreaMapper;
    @InjectMock
    OperationalAreaRepository operationalAreaRepository;
    OperationalArea operationalArea;
    OperationalAreaDto operationalAreaDto;
    List<OperationalArea> operationalAreaList;

    @BeforeEach
    void init() {
        this.operationalArea = new OperationalArea(1L, "AREA01", "BRSTMARE0001", "Área 01",
                "Terminal de Múltiplo Uso 1", null);
        OperationalArea operationalArea1 = new OperationalArea(1L, "AREA14", "BRSTMARE0002", "Área 14", "Terminal de passageiros", null);
        this.operationalAreaList = List.of(operationalArea, operationalArea1);
        this.operationalAreaDto = operationalAreaMapper.toDto(operationalArea);
    }

    @Test
    void saveOperationalArea() {
        Mockito.when(operationalAreaRepository.findMooringLocationByAcronym(Mockito.anyString())).thenReturn(null);
        assertEquals(operationalArea.getId(), operationalAreaService.saveOperationalArea(operationalAreaDto).getId());
    }

    @Test
    void saveOperationalAreaWithExistsOperacionalArea() {
        Mockito.when(operationalAreaRepository.findMooringLocationByAcronym(Mockito.anyString())).thenReturn(operationalArea);
        Assertions.assertThrows(WebApplicationException.class, () -> operationalAreaService.saveOperationalArea(operationalAreaDto));
    }

    @Test
    void listAll() {
        Mockito.when(operationalAreaRepository.listAll()).thenReturn(operationalAreaList);
        assertEquals(2, operationalAreaService.listAll().size());
        assertEquals(operationalArea.getAcronymAntaq(), operationalAreaService.listAll().get(0).getAcronymAntaq());
    }

    @Test
    void findMooringLocationByAcronym() {
        Mockito.when(operationalAreaRepository.findMooringLocationByAcronym(Mockito.anyString())).thenReturn(operationalArea);
        assertEquals(operationalArea.getAcronym(), operationalAreaService.findMooringLocationByAcronym(operationalAreaDto).getAcronym());
    }

    @Test
    void findMooringLocationByAcronymWithNullParameter() {
        Mockito.when(operationalAreaRepository.findMooringLocationByAcronym(Mockito.anyString())).thenReturn(operationalArea);
        Assertions.assertThrows(WebApplicationException.class, () -> operationalAreaService.saveOperationalArea(operationalAreaDto));
    }

    @Test
    void editMooringLocation() {
        operationalArea.setAcronym("AREA01_edit");
        Mockito.when(operationalAreaRepository.findByIdOptional(Mockito.anyLong())).thenReturn(Optional.ofNullable(operationalArea));
        assertEquals("AREA01_edit", operationalAreaService.editMooringLocation(operationalAreaDto).getAcronym());
    }
}
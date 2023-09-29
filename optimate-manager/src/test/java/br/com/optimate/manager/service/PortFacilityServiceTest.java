package br.com.optimate.manager.service;

import br.com.optimate.manager.domain.port.Berth;
import br.com.optimate.manager.domain.port.PortFacility;
import br.com.optimate.manager.domain.type.PortType;
import br.com.optimate.manager.repository.PortFacilityRepository;
import br.com.optimate.manager.dto.PortFacilityDto;
import br.com.optimate.manager.dto.PortFacilityMapper;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@QuarkusTest
class PortFacilityServiceTest {

    @Inject
    PortFacilityService portFacilityService;
    @Inject
    PortFacilityMapper portFacilityMapper;
    @InjectMock
    PortFacilityRepository portFacilityRepository;
    PortFacility portFacility;
    List<PortFacility> portFacilityList;

    @BeforeEach
    void init() {
        Berth berth = new Berth(1L, "101", "101Antaq", "Berço 101", 10.3,
                13.1, 7.5, 1, 13, 2.2, null, null);
        Berth berth1 = new Berth(2L, "102", "102Antaq", "Berço 102", 10.3,
                13.1, 7.5, 1, 13, 2.2, null, null);
        List<Berth> berthList = new ArrayList<>();
        berthList.add(berth);
        berthList.add(berth1);
        this.portFacility = new PortFacility(1L, "STM", "STM00290", "Porto de Santarém", true, berthList, PortType.PUBLIC_PIER, null);
        this.portFacilityList = List.of(portFacility);
    }

    @Test
    void savePortFacility() {
        Mockito.when(portFacilityRepository.findPortFacilityByAcronymPort(Mockito.anyString())).thenReturn(null);
        Assertions.assertEquals(portFacility.getAcronymPort(), portFacilityService.savePortFacility(portFacilityMapper.toDto(portFacility)).getAcronymPort());
    }

    @Test
    void savePortFacilityWithExistsPort() {
        PortFacilityDto portFacilityDto = portFacilityMapper.toDto(portFacility);
        Mockito.when(portFacilityRepository.findPortFacilityByAcronymPort(Mockito.anyString())).thenReturn(portFacility);
        Assertions.assertThrows(WebApplicationException.class, () -> portFacilityService.savePortFacility(portFacilityDto));
    }

    @Test
    void listAll() {
        Mockito.when(portFacilityRepository.listAll()).thenReturn(portFacilityList);
        Assertions.assertEquals(1, portFacilityService.listAll().size());
    }

    @Test
    void findPortFacilityByName() {
        Mockito.when(portFacilityRepository.findByIdOptional(Mockito.anyLong())).thenReturn(Optional.ofNullable(portFacility));
        Assertions.assertEquals(portFacility.getName(), portFacilityService.findPortFacilityByName(portFacilityMapper.toDto(portFacility)).getName());
    }

    @Test
    void editPortFacility() {
        portFacility.setName("Porto de Santarém editado");
        Mockito.when(portFacilityRepository.findByIdOptional(Mockito.anyLong())).thenReturn(Optional.ofNullable(portFacility));
        Assertions.assertEquals("Porto de Santarém editado", portFacilityService.editPortFacility(portFacilityMapper.toDto(portFacility)).getName());
        Mockito.verify(portFacilityRepository).persist(portFacility);
    }

    @Test
    void editPortFacilityWithNullParameter() {
        portFacility.setName(null);
        Mockito.when(portFacilityRepository.findPortFacilityByName(Mockito.anyString())).thenReturn(portFacility);
        Assertions.assertThrows(WebApplicationException.class, () -> portFacilityService.findPortFacilityByName(portFacilityMapper.toDto(portFacility)));

    }
}
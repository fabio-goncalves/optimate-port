package br.com.optimate.manager.service;

import br.com.optimate.manager.domain.port.Berth;
import br.com.optimate.manager.domain.port.OperationalArea;
import br.com.optimate.manager.domain.port.PortFacility;
import br.com.optimate.manager.domain.type.PortType;
import br.com.optimate.manager.dto.PortFacilityDto;
import br.com.optimate.manager.dto.PortFacilityMapper;
import br.com.optimate.manager.repository.PortFacilityRepository;
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
    PortFacilityDto portFacilityDto;
    List<PortFacility> portFacilityList;

    @BeforeEach
    void init() {
        Berth berth = new Berth.BerthBuilder("101", "101Antaq", "Berço 101")
                .length(10.3)
                .airDraftMax(13.1)
                .draftMax(7.5)
                .initialHeader(1)
                .finalHeader(13).build();
        Berth berth1 = new Berth.BerthBuilder("102", "102Antaq", "Berço 102")
                .length(10.3)
                .airDraftMax(13.1)
                .draftMax(7.5)
                .initialHeader(1)
                .finalHeader(13).build();
        List<Berth> berthList = new ArrayList<>();
        berthList.add(berth);
        berthList.add(berth1);
        OperationalArea operationalArea = new OperationalArea(1L, "BEL - 01", "BRBELARE0001", "Terminal de Múltiplo Uso - 01 - BEL", null);
        List<OperationalArea> operationalAreaList = List.of(operationalArea);
        this.portFacility = new PortFacility("STM", "STM00290", "Porto de Santarém", true, berthList, PortType.PUBLIC_PIER, operationalAreaList);
        this.portFacilityList = List.of(portFacility);
        this.portFacilityDto = portFacilityMapper.toDto(portFacility);
    }

    @Test
    void savePortFacility() {
        Mockito.when(portFacilityRepository.findPortFacilityByAcronymPort(Mockito.anyString())).thenReturn(null);
        Assertions.assertEquals(portFacility.getAcronymPort(), portFacilityService.savePortFacility(portFacilityDto).getAcronymPort());
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
        portFacilityDto.setId(1L);
        Mockito.when(portFacilityRepository.findByIdOptional(Mockito.anyLong())).thenReturn(Optional.ofNullable(portFacility));
        Assertions.assertEquals(portFacility.getName(), portFacilityService.findPortFacilityByName(portFacilityDto).getName());
    }

    @Test
    void editPortFacility() {
        portFacilityDto.setId(1L);
        portFacilityDto.setName("Porto de Santarém editado");
        Mockito.when(portFacilityRepository.findByIdOptional(Mockito.anyLong())).thenReturn(Optional.ofNullable(portFacility));
        Mockito.when(portFacilityService.editPortFacility(portFacilityDto)).thenAnswer(i -> {
            PortFacility portFacility1 = i.getArgument(0);
            portFacility1.setId(1L);
            portFacility1.setName("Porto de Santarém editado");
            return portFacility1;
        });
        Assertions.assertEquals("Porto de Santarém editado", portFacilityService.editPortFacility(portFacilityDto).getName());
        Mockito.verify(portFacilityRepository).persist(portFacility);
    }

    @Test
    void editPortFacilityWithNullParameter() {
        portFacility.setName(null);
        Mockito.when(portFacilityRepository.findPortFacilityByName(Mockito.anyString())).thenReturn(portFacility);
        Assertions.assertThrows(WebApplicationException.class, () -> portFacilityService.findPortFacilityByName(portFacilityDto));

    }
}
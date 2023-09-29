package br.com.optimate.manager.service;

import br.com.optimate.manager.domain.port.Berth;
import br.com.optimate.manager.domain.port.MooringLocation;
import br.com.optimate.manager.repository.MooringLocationRepository;
import br.com.optimate.manager.dto.MooringLocationMapper;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
class MooringLocationServiceTest {

    @Inject
    MooringLocationService mooringLocationService;
    @Inject
    MooringLocationMapper mooringLocationMapper;
    @InjectMock
    MooringLocationRepository mooringLocationRepository;
    MooringLocation mooringLocation;
    List<MooringLocation> mooringLocationList;

    @BeforeEach
    void init() {
        Berth berth = new Berth(1L, "101", "101Antaq", "Berço 101", 10.3,
                13.1, 7.5, 1, 13, 2.2, null, null);
        Berth berth1 = new Berth(2L, "102", "102Antaq", "Berço 102", 10.3,
                13.1, 7.5, 1, 13, 2.2, null, null);
        List<Berth> berthList = new ArrayList<>();
        berthList.add(berth);
        berthList.add(berth1);
        this.mooringLocation = new MooringLocation(1L, "STM.CP", "Cais Público", berthList);
        MooringLocation mooringLocation1 = new MooringLocation(1L, "STM.FOGAZ", "Arrendamento Fogaz", berthList);
        this.mooringLocationList = List.of(mooringLocation, mooringLocation1);
    }

    @Test
    void saveMooringLocation() {
        Mockito.when(mooringLocationRepository.findMooringLocationByAcronym(Mockito.anyString())).thenReturn(null);
        Assertions.assertSame(mooringLocationMapper.toDto(mooringLocation).getId(),
                mooringLocationService.saveMooringLocation(mooringLocationMapper.toDto(mooringLocation)).getId());
    }

    @Test
    void saveMooringLocationWithExists() {
        Mockito.when(mooringLocationRepository.findMooringLocationByAcronym(Mockito.anyString())).thenReturn(mooringLocation);
        Assertions.assertThrows(WebApplicationException.class, () -> mooringLocationService.saveMooringLocation(mooringLocationMapper.toDto(mooringLocation)));
    }


    @Test
    void listAll() {
        Mockito.when(mooringLocationRepository.listAll()).thenReturn(mooringLocationList);
        assertEquals(mooringLocationMapper.toDtoList(mooringLocationList).size(), mooringLocationService.listAll().size());
        Mockito.verify(mooringLocationRepository).listAll();
    }

    @Test
    void findMooringLocationByAcronym() {
        Mockito.when(mooringLocationRepository.findMooringLocationByAcronym(Mockito.anyString())).thenReturn(mooringLocation);
        assertEquals(mooringLocation.getAcronymMooring(), mooringLocationService
                .findMooringLocationByAcronym(mooringLocationMapper.toDto(mooringLocation)).getAcronymMooring());
    }

    @Test
    void findMooringLocationByAcronymWithNullAcronym() {
        mooringLocation.setAcronymMooring(null);
        Mockito.when(mooringLocationRepository.findMooringLocationByAcronym(Mockito.anyString())).thenReturn(mooringLocation);
        assertThrows(WebApplicationException.class, () -> mooringLocationService.findMooringLocationByAcronym(mooringLocationMapper.toDto(mooringLocation)));
    }

    @Test
    void editMooringLocation() {
        mooringLocation.setDescription("Cais privado");
        Mockito.when(mooringLocationRepository.findByIdOptional(Mockito.anyLong())).thenReturn(Optional.of(mooringLocation));
        assertEquals("Cais privado", mooringLocationService.editMooringLocation(mooringLocationMapper.toDto(mooringLocation)).getDescription());
        Mockito.verify(mooringLocationRepository).persist(mooringLocation);
    }

    @Test
    void editMooringLocationNotFound() {
        mooringLocation.setDescription("Cais privado");
        Mockito.when(mooringLocationRepository.findByIdOptional(Mockito.anyLong())).thenReturn(null);
        assertThrows(WebApplicationException.class, () -> mooringLocationService.findMooringLocationByAcronym(mooringLocationMapper.toDto(mooringLocation)));
    }

    @Test
    void addBerth() {
        Berth berth = new Berth(3L, "103", "103Antaq", "Berço 103", 10.3,
                13.1, 7.5, 1, 13, 2.2, null, null);
        Mockito.when(mooringLocationRepository.findByIdOptional(Mockito.anyLong())).thenReturn(Optional.of(mooringLocation));
        assertEquals(3, mooringLocationService.addBerth(mooringLocationMapper.toDto(mooringLocation), berth).getBerth().size());
        assertEquals("103", mooringLocationService.addBerth(mooringLocationMapper.toDto(mooringLocation), berth).getBerth().get(2).getAcronymBerth());
    }
}
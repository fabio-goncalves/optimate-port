package br.com.optimate.manager.service;

import br.com.optimate.manager.domain.port.Berth;
import br.com.optimate.manager.domain.port.MooringLocation;
import br.com.optimate.manager.dto.MooringLocationDto;
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
    MooringLocationDto mooringLocationDto;
    List<MooringLocation> mooringLocationList = new ArrayList<>();

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
        this.mooringLocation = new MooringLocation(1L, "BEL - 01", "BRBELARE0001", "Terminal de Múltiplo Uso - 01 - BEL", berthList);
        MooringLocation mooringLocation1 = new MooringLocation(1L, "BEL - 02", "BRBELARE0002", "Terminal de Múltiplo Uso - 02 - BEL", berthList);
        this.mooringLocationList = List.of(mooringLocation, mooringLocation1);
        this.mooringLocationDto = mooringLocationMapper.toDto(mooringLocation);
    }

    @Test
    void saveMooringLocation() {
        Mockito.when(mooringLocationRepository.findMooringLocationByAcronym(Mockito.anyString())).thenReturn(null);
        Mockito.when(mooringLocationService.saveMooringLocation(mooringLocationDto)).thenAnswer(i -> {
            MooringLocation mooringLocation1 = (MooringLocation) i.getArgument(0);
            mooringLocation1.setId(1L);
            return mooringLocation1;
        });
        Assertions.assertSame(mooringLocationDto.getId(),
                mooringLocationService.saveMooringLocation(mooringLocationDto).getId());
    }

    @Test
    void saveMooringLocationWithExists() {
        Mockito.when(mooringLocationRepository.findMooringLocationByAcronym(Mockito.anyString())).thenReturn(mooringLocation);
        Assertions.assertThrows(WebApplicationException.class, () -> mooringLocationService.saveMooringLocation(mooringLocationDto));
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
                .findMooringLocationByAcronym(mooringLocationDto).getAcronymMooring());
    }

    @Test
    void findMooringLocationByAcronymWithNullAcronym() {
        MooringLocation edittedMooringLocation = new MooringLocation(1L, "BEL - 03", null, "Terminal de Múltiplo Uso - 03 - BEL", null);
        MooringLocationDto edittedMooringLocationDto = mooringLocationMapper.toDto(edittedMooringLocation);
        Mockito.when(mooringLocationRepository.findMooringLocationByAcronym(Mockito.anyString())).thenReturn(edittedMooringLocation);
        assertThrows(WebApplicationException.class, () -> mooringLocationService.findMooringLocationByAcronym(edittedMooringLocationDto));
    }

    @Test
    void editMooringLocation() {
        MooringLocation edittedMooringLocation = new MooringLocation(1L, "BEL - 03", "BRBELARE0003", "Terminal de Múltiplo Uso - 03 - BEL", null);
        MooringLocationDto edittedMooringLocationDto = mooringLocationMapper.toDto(edittedMooringLocation);
        Mockito.when(mooringLocationRepository.findByIdOptional(Mockito.anyLong())).thenReturn(Optional.of(edittedMooringLocation));
        assertEquals("Terminal de Múltiplo Uso - 03 - BEL", mooringLocationService.editMooringLocation(edittedMooringLocationDto).getDescription());
    }

    @Test
    void editMooringLocationNotFound() {
        mooringLocation.setDescription("Cais privado");
        Mockito.when(mooringLocationRepository.findByIdOptional(Mockito.anyLong())).thenReturn(null);
        assertThrows(WebApplicationException.class, () -> mooringLocationService.findMooringLocationByAcronym(mooringLocationDto));
    }

    @Test
    void addBerth() {
        Berth berth = new Berth.BerthBuilder("103", "103Antaq", "Berço 103")
                .length(10.3)
                .airDraftMax(13.1)
                .draftMax(7.5)
                .initialHeader(1)
                .finalHeader(13).build();
        Mockito.when(mooringLocationRepository.findByIdOptional(Mockito.anyLong())).thenReturn(Optional.of(mooringLocation));
        assertEquals(3, mooringLocationService.addBerth(mooringLocationDto, berth).getBerthList().size());
        assertEquals("103", mooringLocationService.addBerth(mooringLocationDto, berth).getBerthList().get(2).getAcronymBerth());
    }
}
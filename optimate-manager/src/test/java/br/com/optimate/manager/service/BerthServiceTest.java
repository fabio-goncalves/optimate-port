package br.com.optimate.manager.service;

import br.com.optimate.manager.domain.port.Berth;
import br.com.optimate.manager.dto.BerthDto;
import br.com.optimate.manager.repository.BerthRepository;
import br.com.optimate.manager.dto.BerthMapper;
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
class BerthServiceTest {

    @Inject
    BerthService berthService;
    @Inject
    BerthMapper berthMapper;
    @InjectMock
    BerthRepository berthRepositoryMock;
    Berth berth;
    BerthDto berthDto;
    List<Berth> berthList;


    @BeforeEach
    void init() {
        this.berth = new Berth.BerthBuilder("101", "101Antaq", "Berço 101")
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
        this.berthList = List.of(berth, berth1);
        this.berthDto = berthMapper.toDto(berth);
    }

    @Test
    void saveBerth() {
        Mockito.when(berthRepositoryMock.findBerthByacronymBerth(Mockito.any())).thenReturn(null);
        Assertions.assertEquals(berth.getAcronymBerth(), berthService.saveBerth(berthMapper.toDto(berth)).getAcronymBerth());
    }

    @Test
    void saveBerthWithExistsBerth() {
        Mockito.when(berthRepositoryMock.findBerthByacronymBerth(Mockito.any())).thenReturn(berth);
        Assertions.assertThrows(WebApplicationException.class, () -> berthService.saveBerth(berthDto));
    }

    @Test
    void listAll() {
        Mockito.when(berthRepositoryMock.listAll()).thenReturn(berthList);
        Assertions.assertEquals(2, berthService.listAll().size());
        Assertions.assertEquals(berthMapper.toDto(berth).getAcronymBerth(), berthService.listAll().get(0).getAcronymBerth());
    }

    @Test
    void findBerthByName() {
        Mockito.when(berthRepositoryMock.findBerthByName(Mockito.anyString())).thenReturn(berth);
        Assertions.assertSame(berthMapper.toDto(berth).getName(), berthService.findBerthByName(berthMapper.toDto(berth)).getName());
    }

    @Test
    void editBerth() {
        Berth editBerth = new Berth.BerthBuilder("101", "101Antaq", "Berço 105")
                .id(1L)
                .length(10.3)
                .airDraftMax(13.1)
                .draftMax(7.5)
                .initialHeader(1)
                .finalHeader(13).build();
        Mockito.when(berthRepositoryMock.findByIdOptional(Mockito.anyLong())).thenReturn(Optional.of(editBerth));
        Assertions.assertEquals("Berço 105", berthService.editBerth(berthMapper.toDto(editBerth)).getName());
    }
}
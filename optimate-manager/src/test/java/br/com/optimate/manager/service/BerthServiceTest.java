package br.com.optimate.manager.service;

import br.com.optimate.manager.domain.port.Berth;
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
    List<Berth> berthList;


    @BeforeEach
    void init() {
        this.berth = new Berth(1L, "101", "101Antaq", "Berço 101", 10.3,
                13.1, 7.5, 1, 13, 2.2, null, null);
        Berth berth1 = new Berth(2L, "102", "102Antaq", "Berço 102", 10.3,
                13.1, 7.5, 1, 13, 2.2, null, null);
        this.berthList = List.of(berth, berth1);
    }

    @Test
    void saveBerth() {
        Mockito.when(berthRepositoryMock.findBerthByacronymBerth(Mockito.any())).thenReturn(null);
        Assertions.assertEquals(berth.getAcronymBerth(), berthService.saveBerth(berthMapper.toDto(berth)).getAcronymBerth());
    }

    @Test
    void saveBerthWithExistsBerth() {
        Mockito.when(berthRepositoryMock.findBerthByacronymBerth(Mockito.any())).thenReturn(berth);
        Assertions.assertThrows(WebApplicationException.class, () -> berthService.saveBerth(berthMapper.toDto(berth)));
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
        berth.setName("Berço 105");
        Mockito.when(berthRepositoryMock.findByIdOptional(Mockito.anyLong())).thenReturn(Optional.of(berth));
        Assertions.assertEquals("Berço 105", berthService.editBerth(berthMapper.toDto(berth)).getName());
    }
}
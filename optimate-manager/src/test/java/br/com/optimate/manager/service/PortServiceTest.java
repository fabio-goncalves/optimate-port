package br.com.optimate.manager.service;

import br.com.optimate.manager.domain.port.Port;
import br.com.optimate.manager.repository.PortRepository;
import br.com.optimate.manager.dto.PortMapper;
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

class PortServiceTest {

    @Inject
    PortService portService;
    @Inject
    PortMapper portMapper;
    @InjectMock
    PortRepository portRepository;
    Port port;
    List<Port> portList;

    @BeforeEach
    void init() {
        this.port = new Port(1L, "ST", "STM", "Porto de Santarém", null);
        Port port1 = new Port(2L, "PC", "PVC", "Porto de Vila do Conde", null);
        this.portList = List.of(port, port1);
    }

    @Test
    void savePort() {
        Mockito.when(portRepository.findPortByName(Mockito.anyString())).thenReturn(null);
        Assertions.assertEquals(port.getName(), portService.savePort(portMapper.toDto(port)).getName());
    }

    @Test
    void savePortWithExitedPort() {
        Mockito.when(portRepository.findPortByName(Mockito.anyString())).thenReturn(port);
        Assertions.assertThrows(WebApplicationException.class, () -> portService.savePort(portMapper.toDto(port)));
    }

    @Test
    void listAll() {
        Mockito.when(portRepository.listAll()).thenReturn(portList);
        Assertions.assertEquals(2, portService.listAll().size());
        Assertions.assertEquals(portList.get(1).getId(), portService.listAll().get(1).getId());
    }

    @Test
    void findPortByName() {
        Mockito.when(portRepository.findPortByName(Mockito.anyString())).thenReturn(port);
        Assertions.assertEquals(port.getName(), portService.findPortByName(portMapper.toDto(port)).getName());
    }

    @Test
    void findPortByNameWithNullParameter() {
        port.setName(null);
        Mockito.when(portRepository.findPortByName(Mockito.anyString())).thenReturn(null);
        Assertions.assertThrows(WebApplicationException.class, () -> portService.findPortByName(portMapper.toDto(port)));
    }

    @Test
    void editPort() {
        port.setName("Porto de Outeiro");
        Mockito.when(portRepository.findByIdOptional(Mockito.anyLong())).thenReturn(Optional.ofNullable(port));
        Assertions.assertEquals("Porto de Outeiro", portService.editPort(portMapper.toDto(port)).getName());
    }
}
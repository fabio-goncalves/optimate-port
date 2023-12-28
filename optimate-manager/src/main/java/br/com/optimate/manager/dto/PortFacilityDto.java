package br.com.optimate.manager.dto;

import br.com.optimate.manager.domain.port.Berth;
import br.com.optimate.manager.domain.port.OperationalArea;
import br.com.optimate.manager.domain.type.PortType;
import lombok.Data;

import java.util.List;

@Data
public class PortFacilityDto {

    private Long id;
    private String acronymPort;
    private String acronymAntaq;
    private String name;
    private Boolean isActive;
    private List<Berth> berthList;
    private PortType portType;
    private List<OperationalArea> operationalAreas;

}

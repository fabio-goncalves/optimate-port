package br.com.optimate.manager.dto;

import br.com.optimate.manager.domain.port.MooringLocation;
import br.com.optimate.manager.domain.port.PortFacility;
import lombok.Data;

@Data
public class BerthDto {

    private Long id;
    private String acronymBerth;
    private String acronymBerthAntaq;
    private String name;
    private Double length;
    private Double  draftMax;
    private Double airDraftMax;
    private Integer initialHeader;
    private Integer finalHeader;
    private Double tolerance;
    private MooringLocation mooringLocation;
    private PortFacility portFacility;

}

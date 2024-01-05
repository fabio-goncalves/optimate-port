package br.com.optimate.manager.dto;


import br.com.optimate.manager.domain.port.PortFacility;
import lombok.Data;

@Data
public class OperationalAreaDto {

    private Long id;
    private String acronym;
    private String acronymAntaq;
    private String description;
    private PortFacility portFacility;

}

package br.com.optimate.manager.dto;



import br.com.optimate.manager.domain.port.Berth;
import br.com.optimate.manager.domain.port.PortFacility;
import lombok.Data;

import java.util.List;

@Data
public class MooringLocationDto {

    private Long id;
    private String acronymMooring;
    private String name;
    private String description;
    private List<Berth> berthList;
}

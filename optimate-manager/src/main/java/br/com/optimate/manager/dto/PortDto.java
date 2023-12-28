package br.com.optimate.manager.dto;

import br.com.optimate.manager.domain.Country;
import lombok.Data;

@Data
public class PortDto {

    private Long id;
    private String bigram;
    private String trigram;
    private String name;
    private Country country;

}

package br.com.optimate.manager.dto;

import br.com.optimate.manager.domain.Country;
import br.com.optimate.manager.domain.company.BusinessArea;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

import java.util.List;

@RegisterForReflection
@Data
public class CompanyDto {

    private Long id;
    private String acronym;
    private String name;
    private String cnpj;
    private String razaoSocial;
    private String inscricaoEstatual;
    private String inscricaoMunicipal;
    private String phoneNumber;
    private String cellPhone;
    private String email;
    private Boolean isActive;
    private Country country;
    private List<BusinessArea> businessAreaList;

}

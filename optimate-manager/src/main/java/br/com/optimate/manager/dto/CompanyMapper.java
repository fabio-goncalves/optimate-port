package br.com.optimate.manager.dto;

import br.com.optimate.manager.domain.company.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CompanyMapper {

    @Mapping(target = "id", source = "id")
    Company toEntity(CompanyDto companyDto);

    @Mapping(target = "id", source = "id")
    CompanyDto toDto(Company company);

    List<Company> toEntityList(List<CompanyDto> companyDtoList);

    List<CompanyDto> toDtoList(List<Company> companyList);

}

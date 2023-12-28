package br.com.optimate.manager.service;

import br.com.optimate.manager.domain.company.BusinessArea;
import br.com.optimate.manager.domain.company.Company;
import br.com.optimate.manager.repository.CompanyRepository;
import br.com.optimate.manager.dto.CompanyDto;
import br.com.optimate.manager.dto.CompanyMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CompanyService implements AbstractService {


    CompanyRepository companyRepository;
    CompanyMapper companyMapper;

    @Inject
    public CompanyService(CompanyRepository companyRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    @Transactional
    public CompanyDto saveCompany(CompanyDto companyDTO) {
        Optional<Company> optionalCompany = Optional.ofNullable(companyRepository.findCompanyByCNPJ(companyDTO.getCnpj()));
        optionalCompany.ifPresent(company -> {
            throw new WebApplicationException("Empresa já cadastrada!", Response.Status.FOUND);
        });
        companyRepository.persist(companyMapper.toEntity(companyDTO));
        return companyDTO;
    }

    public List<CompanyDto> findCompanyByName(String name) {
        return companyMapper.toDtoList(companyRepository.findByCompanyName(name));
    }

    public List<CompanyDto> findCompanyByStatus(Boolean isActive) {
        return companyMapper.toDtoList(companyRepository.findByCompanyStatus(isActive));
    }

    public CompanyDto findCompanyByCNPJ(String cnpj) {
        return companyMapper.toDto(companyRepository.findCompanyByCNPJ(cnpj));
    }

    public List<CompanyDto> findListCompanyByName(String name) {
        return companyMapper.toDtoList(companyRepository.findListCompanyByName(name));
    }
    public List<CompanyDto> listAll() {
        return companyMapper.toDtoList(companyRepository.listAll());
    }

    @Transactional
    public CompanyDto editCompany(CompanyDto companyDTO) {
        Optional<Company> optionalCompany = companyRepository.findByIdOptional(companyDTO.getId());
        Company company = optionalCompany.orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
        companyRepository.persist(companyMapper.toEntity(companyDTO));
        return companyMapper.toDto(company);
    }

    @Transactional
    public void addBusinessArea(Company company, BusinessArea businessArea) {
        company.addBusinessArea(businessArea);
    }
}

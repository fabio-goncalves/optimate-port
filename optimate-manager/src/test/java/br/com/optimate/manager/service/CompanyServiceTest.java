package br.com.optimate.manager.service;

import br.com.optimate.manager.domain.company.BusinessArea;
import br.com.optimate.manager.domain.company.Company;
import br.com.optimate.manager.dto.CompanyDto;
import br.com.optimate.manager.dto.CompanyMapper;
import br.com.optimate.manager.repository.CompanyRepository;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@QuarkusTest
class CompanyServiceTest {

    @Inject
    CompanyService companyService;
    @Inject
    CompanyMapper companyMapper;
    @InjectMock
    CompanyRepository companyRepositoryMock;
    Company company;
    List<Company> companyList;
    CompanyDto companyDto;
    List<BusinessArea> businessAreaList = new ArrayList<>();

    @BeforeEach
    public void init() {
        BusinessArea businessArea = new BusinessArea(1L, "Armador", "Dono da embarcação");
        businessAreaList.add(businessArea);
        this.company = new Company.CompanyBuilder("101", "Empresa de teste", "12345678901", "123 Milhas", true)
                .inscricaoEstatual("109090909090900009")
                .inscricaoMunicipal("12891289128")
                .phoneNumber("913232-1111")
                .email("teste@gmail.com")
                .businessAreaList(businessAreaList)
                .build();
        Company company1 = new Company.CompanyBuilder("102", "Empresa de teste 2", "10987654321", "Unidas", false)
                .inscricaoEstatual("112190909090900009")
                .inscricaoMunicipal("13891289128")
                .email("teste2@gmail.com")
                .businessAreaList(businessAreaList)
                .build();
        this.companyList = List.of(company, company1);
        this.companyDto = companyMapper.toDto(company);
    }

    @Test
    void saveExistsCompanyTest() {
        Mockito.when(companyRepositoryMock.findCompanyByCNPJ(Mockito.anyString())).thenReturn(company);
        Assertions.assertThrows(WebApplicationException.class, () -> companyService.saveCompany(companyDto));
    }

    @Test
    void saveCompanyTest() {
        Mockito.when(companyRepositoryMock.findCompanyByCNPJ(Mockito.anyString())).thenReturn(null);
        Mockito.when(companyService.saveCompany(companyDto)).thenAnswer(i -> {
            Company company1 = (Company) i.getArgument(0);
            company1.setId(1L);
            return company1;
        });
        Company teste = companyMapper.toEntity(companyDto);
        Assertions.assertEquals(company.getId(), companyService.saveCompany(companyDto).getId());
        Assertions.assertEquals(company.getName(), companyService.saveCompany(companyDto).getName());
    }

    @Test
    void findCompanyByCNPJTest() {
        Mockito.when(companyRepositoryMock.findCompanyByCNPJ(company.getCnpj())).thenReturn(company);
        Assertions.assertEquals(company.getId(), companyService.findCompanyByCNPJ(company.getCnpj()).getId());
    }

    @Test
    void listAllCompaniesTest() {
        Mockito.when(companyRepositoryMock.listAll()).thenReturn(companyList);
        Assertions.assertEquals(2, companyService.listAll().size());
        Assertions.assertEquals("12345678901", companyService.listAll().get(0).getCnpj());
    }

    @Test
    void findCompanyByStatusTest() {
        Mockito.when(companyRepositoryMock.findByCompanyStatus(true)).thenReturn(companyList.stream().filter(Company::getIsActive).toList());
        Assertions.assertEquals(1, companyService.findCompanyByStatus(true).size());
        Assertions.assertEquals(true, companyService.findCompanyByStatus(true).get(0).getIsActive());
    }

    @Test
    void findCompanyByNameTest() {
        Mockito.when(companyRepositoryMock.findByCompanyName(company.getName())).thenReturn(companyList.stream()
                .filter(c -> c.getName()
                .equals(company.getName()))
                .toList());
        Assertions.assertEquals("Empresa de teste", companyService.findCompanyByName(company.getName()).get(0).getName());
    }

    @Test
    void editDocumentTest() {
        Company editCompany = new Company.CompanyBuilder(company.getAcronym(), "Nome empresa alterado", company.getCnpj(), company.getRazaoSocial(), company.getIsActive())
                .id(1L)
                .businessAreaList(businessAreaList)
                .build();
        CompanyDto companyDto = companyMapper.toDto(editCompany);
        Mockito.when(companyRepositoryMock.findByIdOptional(Mockito.anyLong())).thenReturn(Optional.of(editCompany));
        Assertions.assertEquals("Nome empresa alterado", companyService.editCompany(companyDto).getName());
    }

    @Test
    void addBussinessAreaTest() {
        BusinessArea businessArea = new BusinessArea();
        businessArea.setName("Operador Portuário");
        businessArea.setDescription("Responsável pela operação");
        companyService.addBusinessArea(company, businessArea);
        Assertions.assertEquals(2, company.getBusinessAreaList().size());
        Assertions.assertEquals("Armador", company.getBusinessAreaList().get(0).getName());
        Assertions.assertEquals("Operador Portuário", company.getBusinessAreaList().get(1).getName());
    }
}

package br.com.optimate.manager.service;

import br.com.optimate.manager.domain.company.BusinessArea;
import br.com.optimate.manager.domain.company.Company;
import br.com.optimate.manager.repository.CompanyRepository;
import br.com.optimate.manager.dto.CompanyMapper;
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

    @BeforeEach
    public void init() {
        BusinessArea businessArea = new BusinessArea();
        businessArea.setName("Armador");
        businessArea.setDescription("Dono da embarcação");
        List<BusinessArea> businessAreaList = new ArrayList<>();
        businessAreaList.add(businessArea);
        this.company = new Company(1L,"101", "Empresa de teste", "12345678901", "123 Milhas", "109090909090900009",
                "12891289128", "913232-1111", "9198477-777", "teste@gmail.com", true, null, businessAreaList);
        Company company1 = new Company(2L,"102", "Empresa de teste 2", "10987654321", "Unidas", "112190909090900009",
                "13891289128", "913232-1112", "9198477-7888", "teste2@gmail.com", false, null, businessAreaList);
        this.companyList = List.of(company, company1);

    }

    @Test
    void saveExistsCompanyTest() {
        Mockito.when(companyRepositoryMock.findCompanyByCNPJ(Mockito.anyString())).thenReturn(company);
        Assertions.assertThrows(WebApplicationException.class, () -> companyService.saveCompany(companyMapper.toDto(company)));
    }

    @Test
    void saveCompanyTest() {
        Mockito.when(companyRepositoryMock.findCompanyByCNPJ(Mockito.anyString())).thenReturn(null);
        Assertions.assertEquals(company.getId(), companyService.saveCompany(companyMapper.toDto(company)).getId());
        Mockito.verify(companyRepositoryMock).persist(company);
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
        Mockito.when(companyRepositoryMock.findByCompanyStatus(true)).thenReturn(companyList.stream().filter(Company::getActive).toList());
        Assertions.assertEquals(1, companyService.findCompanyByStatus(true).size());
        Assertions.assertEquals(true, companyService.findCompanyByStatus(true).get(0).getActive());
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
        company.setName("Nome empresa alterado");
        Mockito.when(companyRepositoryMock.findByIdOptional(Mockito.anyLong())).thenReturn(Optional.of(company));
        Assertions.assertEquals("Nome empresa alterado", companyService.editCompany(companyMapper.toDto(company)).getName());
        Mockito.verify(companyRepositoryMock).persist(company);
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

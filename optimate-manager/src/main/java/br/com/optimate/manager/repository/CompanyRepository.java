package br.com.optimate.manager.repository;

import br.com.optimate.manager.domain.company.Company;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CompanyRepository implements PanacheRepository<Company> {

    public List<Company> findByCompanyName(String name) {
        return list("name", name);
    }
    public List<Company> findByCompanyStatus(Boolean isActive) { return list("isActive", isActive); }
    public List<Company> findListCompanyByName(String name) { return list("name", name); }
    public Company findCompanyByCNPJ(String cnpj) { return find("cnpj", cnpj).firstResult(); }
    public Boolean checkIfCNPJExists(String cnpj) { return  !list("cnpj", cnpj).isEmpty(); }

}

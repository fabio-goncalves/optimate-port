package br.com.optimate.manager.domain.company;

import br.com.optimate.manager.domain.AbstractEntity;
import br.com.optimate.manager.domain.Country;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor(force = true)
@SequenceGenerator(initialValue = 10, name = "seq_company", sequenceName = "seq_company")
public class Company implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_company")
    private Long id;
    @NotNull
    @Size(min = 1, max = 20)
    private final String acronym;
    @NotNull
    @Size(min = 1, max = 80)
    private final String name;
    @NotNull
    @Size(min = 1, max = 80)
    private final String cnpj;
    @NotNull
    @Size(min = 1, max = 80)
    private final String razaoSocial;
    @Size(max = 30)
    private final String inscricaoEstatual;
    @Size(max = 30)
    private final String inscricaoMunicipal;
    @Size(max = 30)
    private final String phoneNumber;
    @Size(max = 30)
    private final String cellPhone;
    @Email
    @Size(max = 100)
    @Column(length = 100, unique = true, nullable = false)
    private final String email;
    @NotNull
    @Column(nullable = false)
    private final Boolean isActive;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private final Country country;
    @ManyToMany
    @JoinTable(name = "company_business",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "business_id"))
    private final List<BusinessArea> businessAreaList;


    private Company(CompanyBuilder companyBuilder) {
        this.id = companyBuilder.id;
        this.acronym = companyBuilder.acronym;
        this.name = companyBuilder.name;
        this.cnpj = companyBuilder.cnpj;
        this.razaoSocial = companyBuilder.razaoSocial;
        this.inscricaoEstatual = companyBuilder.inscricaoEstatual;
        this.inscricaoMunicipal = companyBuilder.inscricaoMunicipal;
        this.phoneNumber = companyBuilder.phoneNumber;
        this.cellPhone = companyBuilder.cellPhone;
        this.email = companyBuilder.email;
        this.isActive = companyBuilder.isActive;
        this.country = companyBuilder.country;
        this.businessAreaList = companyBuilder.businessAreaList.isEmpty() ? new ArrayList<>(): companyBuilder.businessAreaList;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void addBusinessArea(BusinessArea businessArea) {
        this.businessAreaList.add(businessArea);
    }

    public void addBusinessAreaList(List<BusinessArea> businessAreaList) {
        for (BusinessArea businessArea:
                businessAreaList) {
            this.addBusinessArea(businessArea);
        }
    }

    @Override
    public String toString() {
        return "Company{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", cnpj='" + cnpj + '\'' +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(cnpj, company.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnpj);
    }

    public static class CompanyBuilder {

        private Long id;
        private final String acronym;
        private final String name;
        private final String cnpj;
        private final String razaoSocial;
        private String inscricaoEstatual;
        private String inscricaoMunicipal;
        private String phoneNumber;
        private String cellPhone;
        private String email;
        private final Boolean isActive;
        private Country country;
        private List<BusinessArea> businessAreaList;

        public CompanyBuilder(String acronym, String name, String cnpj, String razaoSocial, Boolean isActive) {
            this.acronym = acronym;
            this.name = name;
            this.cnpj = cnpj;
            this.razaoSocial = razaoSocial;
            this.isActive = isActive;
        }


        public CompanyBuilder id (Long id) {
            this.id = id;
            return this;
        }
        public CompanyBuilder inscricaoEstatual(String inscricaoEstatual) {
            this.inscricaoEstatual = inscricaoEstatual;
            return this;
        }

        public CompanyBuilder inscricaoMunicipal(String inscricaoMunicipal) {
            this.inscricaoMunicipal = inscricaoMunicipal;
            return this;
        }

        public CompanyBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public CompanyBuilder cellPhone(String cellPhone) {
            this.cellPhone = cellPhone;
            return this;
        }

        public CompanyBuilder email(String email) {
            this.email = email;
            return this;
        }

        public CompanyBuilder country(Country country) {
            this.country = country;
            return this;
        }

        public CompanyBuilder businessAreaList(List<BusinessArea> businessAreaList) {
            this.businessAreaList = businessAreaList;
            return this;
        }

        public Company build() {
            return new Company(this);
        }
    }

}


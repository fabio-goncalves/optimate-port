package br.com.optimate.manager.domain.company;

import br.com.optimate.manager.domain.AbstractEntity;
import br.com.optimate.manager.domain.Country;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
@SequenceGenerator(initialValue = 10, name = "seq_company", sequenceName = "seq_company")
public class Company implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_company")
    private Long id;
    @NotNull
    @Size(min = 1, max = 20)
    private String acronym;
    @NotNull
    @Size(min = 1, max = 80)
    private String name;
    @NotNull
    @Size(min = 1, max = 80)
    private String cnpj;
    @NotNull
    @Size(min = 1, max = 80)
    private String razaoSocial;
    @Size(max = 30)
    private String inscricaoEstatual;
    @Size(max = 30)
    private String inscricaoMunicipal;
    @Size(max = 30)
    private String phoneNumber;
    @Size(max = 30)
    private String cellPhone;
    @Email
    @Size(max = 100)
    @Column(length = 100, unique = true, nullable = false)
    private String email;
    @NotNull
    @Column(nullable = false)
    private Boolean isActive;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
    @ManyToMany
    @JoinTable(name = "company_business",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "business_id"))
    private List<BusinessArea> businessAreaList;

    public Company() {
    }

    public Company(Long id, String acronym, String name, String cnpj, String razaoSocial, String inscricaoEstatual, String inscricaoMunicipal, String phoneNumber, String cellPhone, String email, Boolean isActive, Country country, List<BusinessArea> businessAreaList) {
        this.id = id;
        this.acronym = acronym;
        this.name = name;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.inscricaoEstatual = inscricaoEstatual;
        this.inscricaoMunicipal = inscricaoMunicipal;
        this.phoneNumber = phoneNumber;
        this.cellPhone = cellPhone;
        this.email = email;
        this.isActive = isActive;
        this.country = country;
        this.businessAreaList = businessAreaList;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getInscricaoEstatual() {
        return inscricaoEstatual;
    }

    public void setInscricaoEstatual(String inscricaoEstatual) {
        this.inscricaoEstatual = inscricaoEstatual;
    }

    public String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<BusinessArea> getBusinessAreaList() {
        return businessAreaList;
    }

    public void setBusinessAreaList(List<BusinessArea> businessAreaList) {
        this.businessAreaList = businessAreaList;
    }

    public void addBusinessArea(BusinessArea businessArea) {
        if (Optional.ofNullable(this.businessAreaList).isEmpty())
            this.businessAreaList = new ArrayList<>();
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

}

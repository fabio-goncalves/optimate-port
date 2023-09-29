package br.com.optimate.manager.dto;


import br.com.optimate.manager.domain.port.PortFacility;

public class OperationalAreaDto {

    private Long id;
    private String acronym;
    private String acronymAntaq;
    private String name;
    private String description;
    private PortFacility portFacility;

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

    public String getAcronymAntaq() {
        return acronymAntaq;
    }

    public void setAcronymAntaq(String acronymAntaq) {
        this.acronymAntaq = acronymAntaq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PortFacility getPortFacility() {
        return portFacility;
    }

    public void setPortFacility(PortFacility portFacility) {
        this.portFacility = portFacility;
    }
}

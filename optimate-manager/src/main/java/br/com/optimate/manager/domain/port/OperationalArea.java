package br.com.optimate.manager.domain.port;

import br.com.optimate.manager.domain.AbstractEntity;
import jakarta.persistence.*;

@Entity
@SequenceGenerator(initialValue = 10, name = "seq_area", sequenceName = "seq_area")
public class OperationalArea implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_area")
    private Long id;
    private String acronym;
    private String acronymAntaq;
    private String name;
    private String description;
    @ManyToOne
    private PortFacility portFacility;

    public OperationalArea() {
    }

    public OperationalArea(Long id, String acronym, String acronymAntaq, String name, String description, PortFacility portFacility) {
        this.id = id;
        this.acronym = acronym;
        this.acronymAntaq = acronymAntaq;
        this.name = name;
        this.description = description;
        this.portFacility = portFacility;
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

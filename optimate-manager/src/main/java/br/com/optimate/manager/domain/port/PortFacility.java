package br.com.optimate.manager.domain.port;

import br.com.optimate.manager.domain.AbstractEntity;
import br.com.optimate.manager.domain.type.PortType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@SequenceGenerator(initialValue = 10, name = "seq_port_facility", sequenceName = "seq_port_facility")
public class PortFacility implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_port_facility")
    private Long id;
    @NotNull
    @Size(min = 1, max = 10)
    private String acronymPort;
    @NotNull
    @Size(min = 1, max = 20)
    private String acronymAntaq;
    @NotNull
    @Size(min = 1, max = 80)
    private String name;
    @NotNull
    @Column(nullable = false)
    private Boolean isActive;
    @OneToMany
    @JoinColumn(name="portFacility_id")
    private List<Berth> berthList;
    private PortType portType;
    @OneToMany
    @JoinColumn(name="operacionalArea_id")
    private List<OperationalArea> operationalAreas;

    public PortFacility() {
    }

    public PortFacility(Long id, String acronymPort, String acronymAntaq, String name, Boolean isActive, List<Berth> berthList, PortType portType, List<OperationalArea> operationalAreas) {
        this.id = id;
        this.acronymPort = acronymPort;
        this.acronymAntaq = acronymAntaq;
        this.name = name;
        this.isActive = isActive;
        this.berthList = berthList;
        this.portType = portType;
        this.operationalAreas = operationalAreas;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcronymPort() {
        return acronymPort;
    }

    public void setAcronymPort(String acronymPort) {
        this.acronymPort = acronymPort;
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<Berth> getBerthList() {
        return berthList;
    }

    public void setBerthList(List<Berth> berthList) {
        this.berthList = berthList;
    }

    public PortType getPortType() {
        return portType;
    }

    public void setPortType(PortType portType) {
        this.portType = portType;
    }

    public List<OperationalArea> getOperationalAreas() {
        return operationalAreas;
    }

    public void setOperationalAreas(List<OperationalArea> operationalAreas) {
        this.operationalAreas = operationalAreas;
    }
}

package br.com.optimate.manager.dto;

import br.com.optimate.manager.domain.port.Berth;
import br.com.optimate.manager.domain.port.OperationalArea;
import br.com.optimate.manager.domain.type.PortType;

import java.util.List;

public class PortFacilityDto {

    private Long id;
    private String acronymPort;
    private String acronymAntaq;
    private String name;
    private Boolean isActive;
    private List<Berth> berthList;
    private PortType portType;
    private List<OperationalArea> operationalAreas;

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

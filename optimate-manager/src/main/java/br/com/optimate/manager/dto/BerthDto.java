package br.com.optimate.manager.dto;

import br.com.optimate.manager.domain.port.MooringLocation;
import br.com.optimate.manager.domain.port.PortFacility;

public class BerthDto {

    private Long id;
    private String acronymBerth;
    private String acronymBerthAntaq;
    private String name;
    private Double length;
    private Double  draftMax;
    private Double airDraftMax;
    private Integer initialHeader;
    private Integer finalHeader;
    private Double tolerance;
    private MooringLocation mooringLocation;
    private PortFacility portFacility;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcronymBerth() {
        return acronymBerth;
    }

    public void setAcronymBerth(String acronymBerth) {
        this.acronymBerth = acronymBerth;
    }

    public String getAcronymBerthAntaq() {
        return acronymBerthAntaq;
    }

    public void setAcronymBerthAntaq(String acronymBerthAntaq) {
        this.acronymBerthAntaq = acronymBerthAntaq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getDraftMax() {
        return draftMax;
    }

    public void setDraftMax(Double draftMax) {
        this.draftMax = draftMax;
    }

    public Double getAirDraftMax() {
        return airDraftMax;
    }

    public void setAirDraftMax(Double airDraftMax) {
        this.airDraftMax = airDraftMax;
    }

    public Integer getInitialHeader() {
        return initialHeader;
    }

    public void setInitialHeader(Integer initialHeader) {
        this.initialHeader = initialHeader;
    }

    public Integer getFinalHeader() {
        return finalHeader;
    }

    public void setFinalHeader(Integer finalHeader) {
        this.finalHeader = finalHeader;
    }

    public Double getTolerance() {
        return tolerance;
    }

    public void setTolerance(Double tolerance) {
        this.tolerance = tolerance;
    }

    public MooringLocation getMooringLocation() {
        return mooringLocation;
    }

    public void setMooringLocation(MooringLocation mooringLocation) {
        this.mooringLocation = mooringLocation;
    }

    public PortFacility getPortFacility() {
        return portFacility;
    }

    public void setPortFacility(PortFacility portFacility) {
        this.portFacility = portFacility;
    }
}

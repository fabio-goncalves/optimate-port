package br.com.optimate.manager.domain.port;

import br.com.optimate.manager.domain.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@SequenceGenerator(initialValue = 10, name = "seq_berth", sequenceName = "seq_berth")
public class Berth implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_berth")
    private Long id;
    @NotNull
    @Size(min = 1, max = 10)
    private String acronymBerth;
    @NotNull
    @Size(min = 1, max = 20)
    private String acronymBerthAntaq;
    @NotNull
    @Size(min = 1, max = 80)
    private String name;
    private Double length;
    private Double  draftMax;
    private Double airDraftMax;
    private Integer initialHeader;
    private Integer finalHeader;
    private Double tolerance;
    @ManyToOne
    @JoinColumn(name = "mooringLocation_id")
    private MooringLocation mooringLocation;
    @ManyToOne
    @JoinColumn(name = "portFacility_id")
    private PortFacility portFacility;

    public Berth() {
    }

    public Berth(Long id, String acronymBerth, String acronymBerthAntaq, String name, Double length, Double draftMax, Double airDraftMax, Integer initialHeader, Integer finalHeader, Double tolerance, MooringLocation mooringLocation, PortFacility portFacility) {
        this.id = id;
        this.acronymBerth = acronymBerth;
        this.acronymBerthAntaq = acronymBerthAntaq;
        this.name = name;
        this.length = length;
        this.draftMax = draftMax;
        this.airDraftMax = airDraftMax;
        this.initialHeader = initialHeader;
        this.finalHeader = finalHeader;
        this.tolerance = tolerance;
        this.mooringLocation = mooringLocation;
        this.portFacility = portFacility;
    }

    @Override
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

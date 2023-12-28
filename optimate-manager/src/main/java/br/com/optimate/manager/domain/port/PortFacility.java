package br.com.optimate.manager.domain.port;

import br.com.optimate.manager.domain.AbstractEntity;
import br.com.optimate.manager.domain.type.PortType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity
@Data
@SequenceGenerator(initialValue = 10, name = "seq_port_facility", sequenceName = "seq_port_facility")
public class PortFacility implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_port_facility")
    private Long id;
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "acronym_port", length = 10, unique = true)
    private String acronymPort;
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "acronym_antaq", unique = true, length = 20)
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
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private PortType portType;
    @OneToMany
    @JoinColumn(name="operacionalArea_id")
    private List<OperationalArea> operationalAreas;

    public PortFacility() {
    }

    public PortFacility(String acronymPort, String acronymAntaq, String name, Boolean isActive, List<Berth> berthList, PortType portType, List<OperationalArea> operationalAreas) {
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
}

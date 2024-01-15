package br.com.optimate.manager.domain.port;

import br.com.optimate.manager.domain.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Objects;

@Entity
@Table(name = "operational_area")
@Data
@SequenceGenerator(initialValue = 10, name = "seq_area", sequenceName = "seq_area")
public class OperationalArea implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_area")
    private Long id;
    @NotNull
    @Size(min = 1, max = 20)
    private String acronym;
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "acronym_antaq", unique = true)
    private String acronymAntaq;
    @Size(max = 150)
    private String description;
    @ManyToOne
    @JoinColumn(name = "port_facility_id")
    private PortFacility portFacility;

    public OperationalArea() {
    }

    public OperationalArea(Long id, String acronym, String acronymAntaq, String description, PortFacility portFacility) {
        this.id = id;
        this.acronym = acronym;
        this.acronymAntaq = acronymAntaq;
        this.description = description;
        this.portFacility = portFacility;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "OperationalArea{" +
                "id=" + id +
                ", acronym='" + acronym + '\'' +
                ", acronymAntaq='" + acronymAntaq + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationalArea that = (OperationalArea) o;
        return Objects.equals(acronym, that.acronym);
    }

    @Override
    public int hashCode() {
        return Objects.hash(acronym);
    }
}

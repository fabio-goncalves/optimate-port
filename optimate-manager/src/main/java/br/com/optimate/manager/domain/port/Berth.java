package br.com.optimate.manager.domain.port;

import br.com.optimate.manager.domain.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@NoArgsConstructor(force = true)
@SequenceGenerator(initialValue = 10, name = "seq_berth", sequenceName = "seq_berth")
public class Berth implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_berth")
    private Long id;
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "acronym_berth", unique = true)
    private final String acronymBerth;
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "acronymBerth_antaq", unique = true)
    private final String acronymBerthAntaq;
    @NotNull
    @Size(min = 1, max = 80)
    private final String name;
    private final Double length;
    @Column(name = "draft_max")
    private final Double  draftMax;
    @Column(name = "air_draft_max")
    private final Double airDraftMax;
    @Column(name = "initial_header")
    private final Integer initialHeader;
    @Column(name = "final_header")
    private final Integer finalHeader;
    private final Double tolerance;
    @ManyToOne
    @JoinColumn(name = "mooringLocation_id")
    private final MooringLocation mooringLocation;
    @ManyToOne
    @JoinColumn(name = "portFacility_id")
    private final PortFacility portFacility;


    private Berth(BerthBuilder berthBuilder) {
        this.id = berthBuilder.id;
        this.acronymBerth = berthBuilder.acronymBerth;
        this.acronymBerthAntaq = berthBuilder.acronymBerthAntaq;
        this.name = berthBuilder.name;
        this.length = berthBuilder.length;
        this.draftMax = berthBuilder.draftMax;
        this.airDraftMax = berthBuilder.airDraftMax;
        this.initialHeader = berthBuilder.initialHeader;
        this.finalHeader = berthBuilder.finalHeader;
        this.tolerance = berthBuilder.tolerance;
        this.mooringLocation = berthBuilder.mooringLocation;
        this.portFacility = berthBuilder.portFacility;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Berth{" +
                "id=" + id +
                ", acronymBerth='" + acronymBerth + '\'' +
                ", acronymBerthAntaq='" + acronymBerthAntaq + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Berth berth = (Berth) o;
        return Objects.equals(id, berth.id) && Objects.equals(acronymBerth, berth.acronymBerth) && Objects.equals(acronymBerthAntaq, berth.acronymBerthAntaq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, acronymBerth, acronymBerthAntaq);
    }

    public static class BerthBuilder {

        private Long id;
        private final String acronymBerth;
        private final String acronymBerthAntaq;
        private final String name;
        private Double length;
        private Double  draftMax;
        private Double airDraftMax;
        private Integer initialHeader;
        private Integer finalHeader;
        private Double tolerance;
        private MooringLocation mooringLocation;
        private PortFacility portFacility;

        public BerthBuilder(String acronymBerth, String acronymBerthAntaq, String name) {
            this.acronymBerth = acronymBerth;
            this.acronymBerthAntaq = acronymBerthAntaq;
            this.name = name;
        }

        public BerthBuilder id(Long id) {
            this.id = id;
            return this;
        }
        public BerthBuilder length(Double length) {
            this.length = length;
            return this;
        }

        public BerthBuilder draftMax(Double draftMax) {
            this.draftMax = draftMax;
            return this;
        }

        public BerthBuilder airDraftMax(Double airDraftMax) {
            this.airDraftMax = airDraftMax;
            return this;
        }

        public BerthBuilder initialHeader(Integer initialHeader) {
            this.initialHeader = initialHeader;
            return this;
        }

        public BerthBuilder finalHeader(Integer finalHeader) {
            this.finalHeader = finalHeader;
            return this;
        }

        public BerthBuilder tolerance(Double tolerance) {
            this.tolerance = tolerance;
            return this;
        }

        public BerthBuilder mooringLocation(MooringLocation mooringLocation) {
            this.mooringLocation = mooringLocation;
            return this;
        }

        public BerthBuilder portFacility(PortFacility portFacility) {
            this.portFacility = portFacility;
            return this;
        }

        public Berth build() {
            return new Berth(this);
        }
    }

}

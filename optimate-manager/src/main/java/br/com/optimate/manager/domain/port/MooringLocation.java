package br.com.optimate.manager.domain.port;

import br.com.optimate.manager.domain.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
@Data
@SequenceGenerator(initialValue = 10, name = "seq_mooring", sequenceName = "seq_mooring")
public class MooringLocation implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_mooring")
    private Long id;
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "acronym_mooring", unique = true)
    private String acronymMooring;
    @Size(max = 150)
    private String description;
    @OneToMany(mappedBy = "mooringLocation")
    private List<Berth> berthList;

    public MooringLocation() {
    }

    public MooringLocation(Long id, String acronymMooring, String description, List<Berth> berthList) {
        this.id = id;
        this.acronymMooring = acronymMooring;
        this.description = description;
        this.berthList = berthList;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "MooringLocation{" +
                "id=" + id +
                ", acronymMooring='" + acronymMooring + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MooringLocation that = (MooringLocation) o;
        return Objects.equals(acronymMooring, that.acronymMooring);
    }

    @Override
    public int hashCode() {
        return Objects.hash(acronymMooring);
    }

    public void addBerth(Berth berth) {
        if (Optional.ofNullable(this.berthList).isEmpty())
                this.berthList = new ArrayList<>();
        this.berthList.add(berth);
    }

    public void addBerthList(List<Berth> berthList) {
        for (Berth berth:
                berthList) {
            this.addBerth(berth);
        }
    }

}

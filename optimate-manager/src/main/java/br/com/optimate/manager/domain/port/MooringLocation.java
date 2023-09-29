package br.com.optimate.manager.domain.port;

import br.com.optimate.manager.domain.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
@SequenceGenerator(initialValue = 10, name = "seq_mooring", sequenceName = "seq_mooring")
public class MooringLocation implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_mooring")
    private Long id;
    @NotNull
    @Size(min = 1, max = 10)
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcronymMooring() {
        return acronymMooring;
    }

    public void setAcronymMooring(String acronymMooring) {
        this.acronymMooring = acronymMooring;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Berth> getBerth() {
        return berthList;
    }

    public void setBerth(List<Berth> berthList) {
        this.berthList = berthList;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MooringLocation mooringLocation = (MooringLocation) o;
        return Objects.equals(acronymMooring, mooringLocation.acronymMooring);
    }

    @Override
    public int hashCode() {
        return Objects.hash(acronymMooring);
    }
}

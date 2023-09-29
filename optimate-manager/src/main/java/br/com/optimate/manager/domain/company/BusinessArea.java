package br.com.optimate.manager.domain.company;

import br.com.optimate.manager.domain.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@SequenceGenerator(initialValue = 10, name = "seq_bussiness_area", sequenceName = "bussiness_area")
public class BusinessArea implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "bussiness_area")
    private Long id;
    @NotNull
    @Size(min = 1, max = 80)
    private String name;
    @Size(max = 250)
    private String description;

    public BusinessArea() {    }

    public BusinessArea(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}

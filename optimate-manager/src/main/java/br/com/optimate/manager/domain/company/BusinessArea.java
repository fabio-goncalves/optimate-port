package br.com.optimate.manager.domain.company;

import br.com.optimate.manager.domain.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@SequenceGenerator(initialValue = 10, name = "seq_bussiness_area", sequenceName = "bussiness_area")
public class BusinessArea implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "bussiness_area")
    private Long id;
    @NotNull
    @Size(min = 1, max = 80)
    private String name;
    @Size(max = 150)
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

}

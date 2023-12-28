package br.com.optimate.manager.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Country implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @NotNull
    @Size(min = 1, max = 80)
    private String name;
    @NotNull
    @Size(min = 1, max = 10)
    private String cod;
    @NotNull
    @Column(nullable = false)
    private Boolean status;

}

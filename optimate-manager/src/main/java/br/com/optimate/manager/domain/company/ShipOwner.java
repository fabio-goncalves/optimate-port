package br.com.optimate.manager.domain.company;

import br.com.optimate.manager.domain.AbstractEntity;
import br.com.optimate.manager.domain.Country;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "ship_owner")
@SequenceGenerator(initialValue = 3400, name = "seq_ship_owner", sequenceName = "seq_ship_owner")
public class ShipOwner implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_ship_owner")
    private Long id;
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "cod", unique = true)
    private String cod;
    @Size(max = 50)
    private String nickname;
    @NotNull
    @Size(max = 100)
    private String name;
    @NotNull
    @Column(name = "is_active")
    private Boolean isActive;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

}

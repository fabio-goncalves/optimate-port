package br.com.optimate.manager.domain.port;

import br.com.optimate.manager.domain.AbstractEntity;
import br.com.optimate.manager.domain.Country;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@SequenceGenerator(initialValue = 10, name = "seq_port", sequenceName = "seq_port")
public class Port implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_port")
    private Long id;
    private String bigram;
    private String trigram;
    private String name;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public Port() {
    }

    public Port(Long id, String bigram, String trigram, String name, Country country) {
        this.id = id;
        this.bigram = bigram;
        this.trigram = trigram;
        this.name = name;
        this.country = country;
    }

    @Override
    public Long getId() {
        return id;
    }

}

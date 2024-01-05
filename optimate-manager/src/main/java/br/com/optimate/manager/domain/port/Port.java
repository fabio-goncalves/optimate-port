package br.com.optimate.manager.domain.port;

import br.com.optimate.manager.domain.AbstractEntity;
import br.com.optimate.manager.domain.Country;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Objects;

@Entity
@Data
@SequenceGenerator(initialValue = 25400, name = "seq_port", sequenceName = "seq_port")
public class Port implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_port")
    private Long id;
    @NotNull
    @Size(min = 2, max = 2)
    private String bigram;
    @NotNull
    @Size(min = 3, max = 3)
    private String trigram;
    @NotNull
    @Size(min = 1, max = 80)
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
    public String toString() {
        return "Port{" +
                "id=" + id +
                ", bigram='" + bigram + '\'' +
                ", trigram='" + trigram + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Port port = (Port) o;
        return Objects.equals(id, port.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

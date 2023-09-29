package br.com.optimate.manager.domain.port;

import br.com.optimate.manager.domain.AbstractEntity;
import br.com.optimate.manager.domain.Country;
import jakarta.persistence.*;

@Entity
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
    public void setId(Long id) {
        this.id = id;
    }

    public String getBigram() {
        return bigram;
    }

    public void setBigram(String bigram) {
        this.bigram = bigram;
    }

    public String getTrigram() {
        return trigram;
    }

    public void setTrigram(String trigram) {
        this.trigram = trigram;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}

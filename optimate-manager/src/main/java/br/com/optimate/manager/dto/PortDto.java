package br.com.optimate.manager.dto;

import br.com.optimate.manager.domain.Country;

public class PortDto {

    private Long id;
    private String bigram;
    private String trigram;
    private String name;
    private Country country;

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

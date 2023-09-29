package br.com.optimate.manager.dto;



import br.com.optimate.manager.domain.port.Berth;

import java.util.List;

public class MooringLocationDto {

    private Long id;
    private String acronymMooring;
    private String description;
    private List<Berth> berthList;

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
}

package com.example.testapp.Domain.Model;

import com.example.testapp.DTO.NbrbCurrencyDTO;

public class NbrbCurrencyModel {

    private Integer id;

    private String abbreviation;

    private Integer scale;

    private String name;

    private Double rate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public NbrbCurrencyModel(NbrbCurrencyDTO dto) {
        id = dto.getId();
        abbreviation = dto.getAbbreviation();
        scale = dto.getScale();
        name = dto.getName();
        rate = dto.getRate();
    }
}

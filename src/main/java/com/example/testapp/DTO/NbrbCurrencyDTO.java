package com.example.testapp.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class NbrbCurrencyDTO implements Serializable {

    @JsonProperty("Cur_ID")
    private Integer id;

    @JsonProperty("Cur_Abbreviation")
    private String abbreviation;

    @JsonProperty("Cur_Scale")
    private Integer scale;

    @JsonProperty("Cur_Name")
    private String name;

    @JsonProperty("Cur_OfficialRate")
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
}

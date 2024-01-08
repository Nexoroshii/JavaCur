package com.example.testapp.Domain.Model;

import com.example.testapp.Database.Entity.CurrencyEntity;

public class CurrencyModel {

    private Integer id;

    private String abbreviation;

    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CurrencyModel(CurrencyEntity currencyEntity) {
        id = currencyEntity.getId();
        abbreviation = currencyEntity.getAbbreviation();
        name = currencyEntity.getName();
    }
}

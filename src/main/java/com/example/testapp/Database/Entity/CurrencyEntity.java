package com.example.testapp.Database.Entity;

import jakarta.persistence.*;

@Table(name = "Currencies")
@Entity
public class CurrencyEntity {

    @Id
    @Column(name = "Currency_ID")
    private Integer id;

    @Column(name = "Cur_Abbreviation")
    private String abbreviation;

    @Column(name = "Cur_Name")
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
}

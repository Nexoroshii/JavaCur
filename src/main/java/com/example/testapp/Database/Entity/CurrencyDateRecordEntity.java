package com.example.testapp.Database.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "currency_date_records")
@Entity
public class CurrencyDateRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "currency_date_record_id")
    private Integer id;

    @Column(name = "currency_date")
    private LocalDate date;

    @OneToMany(mappedBy = "currencyDateRecord")
    private List<CurrencyRecordEntity> currencyRecordsList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<CurrencyRecordEntity> getCurrencyRecordsList() {
        if (currencyRecordsList == null) {
            currencyRecordsList = new ArrayList<>();
        }
        return currencyRecordsList;
    }

    public void setCurrencyRecordsList(List<CurrencyRecordEntity> currencyRecordsList) {
        this.currencyRecordsList = currencyRecordsList;
    }
}

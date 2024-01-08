package com.example.testapp.Domain.Model;

import com.example.testapp.Database.Entity.CurrencyDateRecordEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CurrencyDateRecordModel {

    private Integer id;

    private LocalDate date;

    private List<CurrencyRecordModel> currencyRecords;

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

    public List<CurrencyRecordModel> getCurrencyRecords() {
        if (currencyRecords == null) {
            currencyRecords = new ArrayList<>();
        }
        return currencyRecords;
    }

    public void setCurrencyRecords(List<CurrencyRecordModel> currencyRecords) {
        this.currencyRecords = currencyRecords;
    }

    public CurrencyDateRecordModel(CurrencyDateRecordEntity currencyDateRecordEntity) {
        id = currencyDateRecordEntity.getId();
        date = currencyDateRecordEntity.getDate();
        currencyRecords = currencyDateRecordEntity.getCurrencyRecordsList().stream()
                .map(CurrencyRecordModel::new)
                .collect(Collectors.toList());
    }
}

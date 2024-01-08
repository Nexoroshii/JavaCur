package com.example.testapp.Domain.Model;

import com.example.testapp.Database.Entity.CurrencyRecordEntity;

public class CurrencyRecordModel {

    private Integer id;

    private Integer scale;

    private Double rate;

    private CurrencyModel currency;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public CurrencyModel getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyModel currency) {
        this.currency = currency;
    }

    public CurrencyRecordModel(CurrencyRecordEntity currencyRecordEntity) {
        id = currencyRecordEntity.getId();
        scale = currencyRecordEntity.getScale();
        rate = currencyRecordEntity.getRate();
        currency = new CurrencyModel(currencyRecordEntity.getCurrency());
    }
}

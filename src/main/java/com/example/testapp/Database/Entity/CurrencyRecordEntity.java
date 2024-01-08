package com.example.testapp.Database.Entity;

import jakarta.persistence.*;

@Table(name = "currency_records")
@Entity
public class CurrencyRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "currency_record_id")
    private Integer id;

    @Column(name = "cur_scale")
    private Integer scale;

    @Column(name = "cur_official_rate")
    private Double rate;

    @ManyToOne
    @JoinColumn(name="currency_id", nullable=false)
    private CurrencyEntity currency;

    @ManyToOne
    @JoinColumn(name="currency_date_id", nullable=false)
    private CurrencyDateRecordEntity currencyDateRecord;

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

    public CurrencyEntity getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEntity currency) {
        this.currency = currency;
    }

    public CurrencyDateRecordEntity getCurrencyDateRecord() {
        return currencyDateRecord;
    }

    public void setCurrencyDateRecord(CurrencyDateRecordEntity currencyDateRecord) {
        this.currencyDateRecord = currencyDateRecord;
    }

    public CurrencyRecordEntity() {
    }
}

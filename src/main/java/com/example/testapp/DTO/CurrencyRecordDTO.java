package com.example.testapp.DTO;

import com.example.testapp.Domain.Model.CurrencyRecordModel;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class CurrencyRecordDTO implements Serializable {

    @JsonProperty("curId")
    private Integer id;

    @JsonProperty("curAbbreviation")
    private String abbreviation;

    @JsonProperty("curScale")
    private Integer scale;

    @JsonProperty("curName")
    private String name;

    @JsonProperty("curOfficialRate")
    private Double rate;

    public CurrencyRecordDTO(CurrencyRecordModel currencyRecordModel) {
        id = currencyRecordModel.getId();
        scale = currencyRecordModel.getScale();
        rate = currencyRecordModel.getRate();
        abbreviation = currencyRecordModel.getCurrency().getAbbreviation();
        name = currencyRecordModel.getCurrency().getName();
    }
}

package com.example.testapp.Domain.Service;

import com.example.testapp.Database.Entity.CurrencyDateRecordEntity;
import com.example.testapp.Database.Entity.CurrencyEntity;
import com.example.testapp.Database.Entity.CurrencyRecordEntity;
import com.example.testapp.Database.Repository.CurrencyDateRecordRepository;
import com.example.testapp.Database.Repository.CurrencyRecordRepository;
import com.example.testapp.Database.Repository.CurrencyRepository;
import com.example.testapp.Domain.Model.CurrencyDateRecordModel;
import com.example.testapp.Domain.Model.CurrencyRecordModel;
import com.example.testapp.Domain.Model.NbrbCurrencyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class CurrencyService {

    @Autowired
    CurrencyDateRecordRepository currencyDateRecordRepository;

    @Autowired
    CurrencyRecordRepository currencyRecordRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    public Boolean checkIfCurrencyDateRecordExists(LocalDate localDate) {
        var currencyDateRecords = currencyDateRecordRepository.findByLocalDate(localDate);
        return !currencyDateRecords.isEmpty();
    }

    public void saveNbrbCurrencyRecords(LocalDate localDate, List<NbrbCurrencyModel> nbrbCurrencyModels) {
        var currencies = nbrbCurrencyModels.stream()
                .map(nbrbCurrencyModel -> {
                    var currency = new CurrencyEntity();
                    currency.setId(nbrbCurrencyModel.getId());
                    currency.setName(nbrbCurrencyModel.getName());
                    currency.setAbbreviation(nbrbCurrencyModel.getAbbreviation());
                    return currency;
                })
                .toList();
        final var newCurrencies = currencyRepository.saveAll(currencies);

        var currencyDateRecord = new CurrencyDateRecordEntity();
        currencyDateRecord.setDate(localDate);
        final var newCurrencyDateRecord = currencyDateRecordRepository.save(currencyDateRecord);

        var currencyRecords = nbrbCurrencyModels.stream()
                .map(nbrbCurrencyModel -> {
                    var currencyRecord = new CurrencyRecordEntity();
                    var currency = newCurrencies.stream()
                            .filter(currencyEntity -> currencyEntity.getId().equals(nbrbCurrencyModel.getId()))
                            .findFirst();
                    currencyRecord.setCurrencyDateRecord(newCurrencyDateRecord);
                    currencyRecord.setCurrency(currency.get());
                    currencyRecord.setRate(nbrbCurrencyModel.getRate());
                    currencyRecord.setScale(nbrbCurrencyModel.getScale());
                    return currencyRecord;
                })
                .toList();
        final var newCurrencyRecords = currencyRecordRepository.saveAll(currencyRecords);
    }

    public List<CurrencyRecordModel> getCurrencyRecordsByDate(LocalDate localDate) {
        var currencyDateRecordEntities = currencyDateRecordRepository.findByLocalDate(localDate);
        var currencyDateRecordEntityOptional = currencyDateRecordEntities.stream().findFirst();
        if (currencyDateRecordEntityOptional.isPresent()) {
            var currencyDateRecordEntity = currencyDateRecordEntityOptional.get();
            return new CurrencyDateRecordModel(currencyDateRecordEntity).getCurrencyRecords();
        }
        return new ArrayList<>();
    }
}

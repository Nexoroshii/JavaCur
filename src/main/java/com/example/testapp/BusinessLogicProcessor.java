package com.example.testapp;

import com.example.testapp.Domain.Model.CurrencyRecordModel;
import com.example.testapp.Domain.Service.CurrencyService;
import com.example.testapp.Domain.Service.NbrbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BusinessLogicProcessor {

    @Autowired
    NbrbService nbrbService;

    @Autowired
    CurrencyService currencyService;

    public Boolean checkIfCurrencyDateRecordExists(LocalDate localDate) {
        var isDataExists = currencyService.checkIfCurrencyDateRecordExists(localDate);
        if (isDataExists) {
            return true;
        }
        var downloadedData = nbrbService.getCurrenciesOnDate(localDate);
        if (downloadedData.isEmpty()) {
            return false;
        }
        currencyService.saveNbrbCurrencyRecords(localDate, downloadedData);
        return true;
    }

    public Optional<CurrencyRecordModel> getCurrencyDataByDateAndId(LocalDate localDate, Integer id) {
        return currencyService.getCurrencyRecordsByDate(localDate).stream()
                .filter(currencyRecord -> currencyRecord.getCurrency().getId().equals(id))
                .findFirst();
    }
}

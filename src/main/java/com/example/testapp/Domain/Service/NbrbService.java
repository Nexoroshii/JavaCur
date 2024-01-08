package com.example.testapp.Domain.Service;

import com.example.testapp.Domain.Model.NbrbCurrencyModel;
import com.example.testapp.Gateaway.NbrbGateaway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NbrbService {

    @Autowired
    NbrbGateaway nbrbGateaway;

    public List<NbrbCurrencyModel> getCurrenciesOnDate(LocalDate localDate) {
        return Arrays.stream(nbrbGateaway.getCurrenciesOnDate(localDate))
                .map(NbrbCurrencyModel::new)
                .collect(Collectors.toList());
    }

}

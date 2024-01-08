package com.example.testapp.Gateaway;

import com.example.testapp.DTO.NbrbCurrencyDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class NbrbGateaway {

    @Value("${gateaway.nbrb.api.url}")
    private String nbrbApiUrl;

    public NbrbCurrencyDTO[] getCurrenciesOnDate(LocalDate localDate) {
        var restTemplate = new RestTemplate();
        var uri = UriComponentsBuilder.fromHttpUrl(nbrbApiUrl + "/exrates/rates")
                .queryParam("ondate",localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .queryParam("periodicity", 0)
                .build();
        return restTemplate.getForEntity(uri.toUri(), NbrbCurrencyDTO[].class).getBody();
    }
}

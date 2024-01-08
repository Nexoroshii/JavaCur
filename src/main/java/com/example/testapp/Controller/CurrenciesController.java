package com.example.testapp.Controller;

import com.example.testapp.BusinessLogicProcessor;
import com.example.testapp.DTO.CurrencyRecordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping("api/currencies")
class CurrenciesController {

    @Autowired
    BusinessLogicProcessor businessLogicProcessor;

    @GetMapping("/check")
    public ResponseEntity<Void> check(@RequestParam String date) {
        try {
            var localDate = parseRequestParamLocalDate(date);
            var result = businessLogicProcessor.checkIfCurrencyDateRecordExists(localDate);
            return new ResponseEntity<>(result ? HttpStatus.OK : HttpStatus.NOT_FOUND);
        }
        catch (DateTimeParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurrencyRecordDTO> getCurrencyData(@RequestParam String date, @PathVariable Integer id) {
        try {
            var localDate = parseRequestParamLocalDate(date);
            var result = businessLogicProcessor.getCurrencyDataByDateAndId(localDate, id);
            return result
                    .map( currencyRecordModel ->
                            new ResponseEntity<>(new CurrencyRecordDTO(currencyRecordModel), HttpStatus.OK)
                    )
                    .orElseGet( () ->
                            new ResponseEntity<>(HttpStatus.NOT_FOUND)
                    );
        }
        catch (DateTimeParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private LocalDate parseRequestParamLocalDate(String localDateString) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(localDateString,formatter);
    }
}
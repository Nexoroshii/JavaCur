package com.example.testapp.Controller;

import com.example.testapp.BusinessLogicProcessor;
import com.example.testapp.DTO.CurrencyRecordDTO;

import org.apache.commons.codec.digest.DigestUtils;
import java.util.zip.CRC32;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
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

            HttpHeaders headers = new HttpHeaders();
            headers.add("CRC32", calculateCRC32(result.toString()));

            return new ResponseEntity<>(headers, result ? HttpStatus.OK : HttpStatus.NOT_FOUND);
        } catch (DateTimeParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurrencyRecordDTO> getCurrencyData(@RequestParam String date, @PathVariable Integer id) {
        try {
            var localDate = parseRequestParamLocalDate(date);
            var result = businessLogicProcessor.getCurrencyDataByDateAndId(localDate, id);

            HttpHeaders headers = new HttpHeaders();
            headers.add("CRC32", calculateCRC32(result.orElse(null).toString()));

            return result
                    .map(currencyRecordModel ->
                            new ResponseEntity<>(new CurrencyRecordDTO(currencyRecordModel), headers, HttpStatus.OK)
                    )
                    .orElseGet(() ->
                            new ResponseEntity<>(headers, HttpStatus.NOT_FOUND)
                    );
        } catch (DateTimeParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private LocalDate parseRequestParamLocalDate(String localDateString) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(localDateString, formatter);
    }

    private String calculateCRC32(String data) {
        CRC32 crc32 = new CRC32();
        crc32.update(data.getBytes(StandardCharsets.UTF_8));
        return Long.toHexString(crc32.getValue());
    }
}

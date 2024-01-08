package com.example.testapp.Database.Repository;

import com.example.testapp.Database.Entity.CurrencyDateRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CurrencyDateRecordRepository extends JpaRepository<CurrencyDateRecordEntity, Integer> {

    @Query(value = "SELECT e.* FROM Currency_Date_Records e WHERE DATE(currency_date) =:localDate", nativeQuery = true)
    List<CurrencyDateRecordEntity> findByLocalDate(LocalDate localDate);

}

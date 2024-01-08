package com.example.testapp.Database.Repository;

import com.example.testapp.Database.Entity.CurrencyRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRecordRepository extends JpaRepository<CurrencyRecordEntity, Integer> {}
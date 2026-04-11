package com.example.hrm.repository;

import com.example.hrm.entity.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HolidayRepository
        extends JpaRepository<Holiday, Long> {

    List<Holiday> findByHolidayDateBetween(
            LocalDate from,
            LocalDate to
    );
    Optional<Holiday> findByHolidayDate(LocalDate date);
}
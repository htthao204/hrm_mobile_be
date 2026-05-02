package com.example.hrm.repository;

import com.example.hrm.entity.Holiday;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HolidayRepository
        extends JpaRepository<Holiday, Integer>, JpaSpecificationExecutor<Holiday> {


    boolean existsByStartDateLessThanEqualAndEndDateGreaterThanEqual(
            LocalDate start,
            LocalDate end
    );

    List<Holiday> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(
            LocalDate to,
            LocalDate from
    );
}
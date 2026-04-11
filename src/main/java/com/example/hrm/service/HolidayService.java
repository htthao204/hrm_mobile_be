package com.example.hrm.service;

import com.example.hrm.entity.Holiday;

import java.time.LocalDate;
import java.util.List;

public interface HolidayService {

    List<Holiday> getAll();

    Holiday create(Holiday holiday);

    Holiday update(Long id, Holiday holiday);

    void delete(Long id);

    boolean isHoliday(LocalDate date);
}
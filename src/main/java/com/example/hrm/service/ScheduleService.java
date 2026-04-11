package com.example.hrm.service;

import com.example.hrm.dto.ScheduleItemDTO;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {

    List<ScheduleItemDTO> getUpcomingSchedule(
            Long employeeId,
            LocalDate from,
            LocalDate to
    );
}
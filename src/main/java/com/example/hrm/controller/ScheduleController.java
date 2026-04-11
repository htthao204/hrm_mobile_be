package com.example.hrm.controller;

import com.example.hrm.dto.ScheduleItemDTO;
import com.example.hrm.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService service;

    @GetMapping("/upcoming")
    public List<ScheduleItemDTO> getUpcoming(
            @RequestParam Long employeeId,
            @RequestParam String from,
            @RequestParam String to
    ) {

        return service.getUpcomingSchedule(
                employeeId,
                LocalDate.parse(from),
                LocalDate.parse(to)
        );
    }
}

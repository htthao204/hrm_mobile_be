package com.example.hrm.controller;

import com.example.hrm.entity.Holiday;
import com.example.hrm.service.HolidayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/holidays")
@RequiredArgsConstructor
public class HolidayController {

    private final HolidayService service;

    @GetMapping
    public List<Holiday> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Holiday create(@RequestBody Holiday holiday) {
        return service.create(holiday);
    }

    @PutMapping("/{id}")
    public Holiday update(
            @PathVariable Long id,
            @RequestBody Holiday holiday
    ) {
        return service.update(id, holiday);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/check")
    public boolean isHoliday(@RequestParam String date) {
        return service.isHoliday(LocalDate.parse(date));
    }
}
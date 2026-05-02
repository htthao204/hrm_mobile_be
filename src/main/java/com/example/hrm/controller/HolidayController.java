package com.example.hrm.controller;

import com.example.hrm.dto.response.HolidayDetailResponse;
import com.example.hrm.entity.Holiday;
import com.example.hrm.service.HolidayService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/holidays")
@RequiredArgsConstructor
public class HolidayController {

    private final HolidayService service;

    // ================= LIST =================
    @GetMapping
    public List<Holiday> getAll() {
        return service.getAll();
    }

    // ================= CREATE =================
    @PostMapping
    public Holiday create(@RequestBody Holiday holiday) {
        return service.create(holiday);
    }

    // ================= UPDATE =================
    @PutMapping("/{id}")
    public Holiday update(
            @PathVariable Integer id,
            @RequestBody Holiday holiday
    ) {
        return service.update(id, holiday);
    }

    // ================= DELETE =================
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    // ================= CHECK =================
    @GetMapping("/check")
    public boolean isHoliday(@RequestParam String date) {
        return service.isHoliday(LocalDate.parse(date));
    }

    // ================= PAGE =================
    @GetMapping("/page")
    public Page<Holiday> getPage(Pageable pageable) {
        return service.getPage(pageable);
    }

    // ================= FILTER =================
    @GetMapping("/filter")
    public Page<Holiday> getHolidays(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month,
            @RequestParam(required = false) Boolean isPaid,
            @RequestParam(required = false) Boolean isGlobal,
            @RequestParam(required = false) String type,
            Pageable pageable
    ) {
        return service.getPageWithFilter(
                year, month, isPaid, isGlobal, type, pageable
        );
    }

    // ================= BULK =================
    @PostMapping("/batch")
    public List<Holiday> createList(
            @RequestBody List<Holiday> holidays
    ) {
        return service.createList(holidays);
    }

    // ✅ DETAIL — dùng Response DTO
    @GetMapping("/{id}")
    public HolidayDetailResponse getDetail(@PathVariable Integer id) {
        return service.getDetail(id);
    }
}
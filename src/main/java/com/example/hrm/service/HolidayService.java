package com.example.hrm.service;

import com.example.hrm.dto.response.HolidayDetailResponse;
import com.example.hrm.entity.Holiday;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface HolidayService {

    List<Holiday> getAll();

    Holiday create(Holiday holiday);

    Holiday update(Integer id, Holiday holiday);

    void delete(Integer id);

    boolean isHoliday(LocalDate date);

    Page<Holiday> getPage(Pageable pageable);

    Page<Holiday> getPageWithFilter(
            Integer year,
            Integer month,
            Boolean isPaid,
            Boolean isGlobal,
            String type,
            Pageable pageable
    );

    List<Holiday> createList(List<Holiday> holidays);

    // ✅ NEW — dùng Response DTO
    HolidayDetailResponse getDetail(Integer id);
}
package com.example.hrm.service.impl;

import com.example.hrm.dto.response.HolidayDetailResponse;
import com.example.hrm.entity.Holiday;
import com.example.hrm.mapper.HolidayMapper;
import com.example.hrm.repository.HolidayRepository;
import com.example.hrm.service.HolidayService;
import com.example.hrm.specification.HolidaySpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HolidayServiceImpl implements HolidayService {

    private final HolidayRepository repository;
    private final HolidayMapper mapper;

    // ================= LIST =================
    @Override
    public List<Holiday> getAll() {
        return repository.findAll();
    }

    // ================= CREATE =================
    @Override
    public Holiday create(Holiday holiday) {
        return repository.save(holiday);
    }

    // ================= UPDATE =================
    @Override
    public Holiday update(Integer id, Holiday holiday) {

        Holiday existing = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Holiday not found with id = " + id)
                );

        existing.setName(holiday.getName());
        existing.setStartDate(holiday.getStartDate());
        existing.setEndDate(holiday.getEndDate());
        existing.setIsGlobal(holiday.getIsGlobal());
        existing.setIsPaid(holiday.getIsPaid());
        existing.setDescription(holiday.getDescription());
        existing.setType(holiday.getType());

        return repository.save(existing);
    }

    // ================= DELETE =================
    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    // ================= CHECK HOLIDAY =================
    @Override
    public boolean isHoliday(LocalDate date) {

        return repository
                .existsByStartDateLessThanEqualAndEndDateGreaterThanEqual(
                        date,
                        date
                );
    }

    // ================= PAGE =================
    @Override
    public Page<Holiday> getPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    // ================= FILTER =================
    @Override
    public Page<Holiday> getPageWithFilter(
            Integer year,
            Integer month,
            Boolean isPaid,
            Boolean isGlobal,
            String type,
            Pageable pageable
    ) {
        return repository.findAll(
                HolidaySpecification.filter(year, month, isPaid, isGlobal, type),
                pageable
        );
    }

    // ================= BULK CREATE =================
    @Override
    public List<Holiday> createList(List<Holiday> holidays) {
        return repository.saveAll(holidays);
    }

    // ================= DETAIL (DTO RESPONSE) =================
    @Override
    public HolidayDetailResponse getDetail(Integer id) {

        Holiday holiday = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Holiday not found with id = " + id)
                );

        return mapper.mapHoliday(holiday);
    }
}
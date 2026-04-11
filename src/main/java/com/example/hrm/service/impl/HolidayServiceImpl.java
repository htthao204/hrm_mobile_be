package com.example.hrm.service.impl;

import com.example.hrm.entity.Holiday;
import com.example.hrm.repository.HolidayRepository;
import com.example.hrm.service.HolidayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HolidayServiceImpl implements HolidayService {

    private final HolidayRepository repository;

    @Override
    public List<Holiday> getAll() {
        return repository.findAll();
    }

    @Override
    public Holiday create(Holiday holiday) {
        return repository.save(holiday);
    }

    @Override
    public Holiday update(Long id, Holiday holiday) {

        Holiday existing = repository.findById(id)
                .orElseThrow();

        existing.setName(holiday.getName());
        existing.setHolidayDate(holiday.getHolidayDate());
        existing.setIsGlobal(holiday.getIsGlobal());
        existing.setIsPaid(holiday.getIsPaid());
        existing.setDescription(holiday.getDescription());

        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean isHoliday(LocalDate date) {
        return repository.findByHolidayDate(date).isPresent();
    }
}
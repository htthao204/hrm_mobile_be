package com.example.hrm.service.impl;

import com.example.hrm.entity.Shift;
import com.example.hrm.repository.ShiftRepository;
import com.example.hrm.service.ShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShiftServiceImpl implements ShiftService {

    private final ShiftRepository shiftRepository;

    @Override
    public List<Shift> getAll() {
        return shiftRepository.findAll();
    }

    @Override
    public Shift getById(Long id) {
        return shiftRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shift not found"));
    }

    @Override
    public Shift create(Shift shift) {
        return shiftRepository.save(shift);
    }

    @Override
    public Shift update(Long id, Shift shift) {

        Shift existing = getById(id);

        existing.setCode(shift.getCode());
        existing.setName(shift.getName());
        existing.setStartTime(shift.getStartTime());
        existing.setEndTime(shift.getEndTime());
        existing.setBreakStart(shift.getBreakStart());
        existing.setBreakEnd(shift.getBreakEnd());
        existing.setWorkingHours(shift.getWorkingHours());
        existing.setAllowLateMinutes(shift.getAllowLateMinutes());
        existing.setAllowEarlyLeaveMinutes(shift.getAllowEarlyLeaveMinutes());
        existing.setIsActive(shift.getIsActive());

        return shiftRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        shiftRepository.deleteById(id);
    }
}
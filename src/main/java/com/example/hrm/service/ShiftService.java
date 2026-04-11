package com.example.hrm.service;

import com.example.hrm.entity.Shift;

import java.util.List;

public interface ShiftService {

    List<Shift> getAll();

    Shift getById(Long id);

    Shift create(Shift shift);

    Shift update(Long id, Shift shift);

    void delete(Long id);
}
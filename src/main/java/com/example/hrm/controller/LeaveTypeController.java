package com.example.hrm.controller;

import com.example.hrm.entity.LeaveType;
import com.example.hrm.service.LeaveTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave-types")
@RequiredArgsConstructor
public class LeaveTypeController {

    private final LeaveTypeService leaveTypeService;

    // =========================
    // Get All Leave Types
    // =========================
    @GetMapping
    public List<LeaveType> getAll() {
        return leaveTypeService.getAll();
    }

    // =========================
    // Get Leave Type By Id
    // =========================
    @GetMapping("/{id}")
    public LeaveType getById(@PathVariable Integer id) {
        return leaveTypeService.getById(id);
    }

    // =========================
    // Create Leave Type
    // =========================
    @PostMapping
    public LeaveType create(@RequestBody LeaveType leaveType) {
        return leaveTypeService.create(leaveType);
    }

    // =========================
    // Update Leave Type
    // =========================
    @PutMapping("/{id}")
    public LeaveType update(
            @PathVariable Integer id,
            @RequestBody LeaveType leaveType
    ) {
        return leaveTypeService.update(id, leaveType);
    }

    // =========================
    // Delete Leave Type
    // =========================
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        leaveTypeService.delete(id);
    }
}
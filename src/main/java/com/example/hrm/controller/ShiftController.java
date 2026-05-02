package com.example.hrm.controller;



import com.example.hrm.entity.Shift;
import com.example.hrm.service.ShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shifts")
@RequiredArgsConstructor
public class ShiftController {

    private final ShiftService shiftService;

    @GetMapping
    public List<Shift> getAll() {
        return shiftService.getAll();
    }

    @GetMapping("/{id}")
    public Shift getById(@PathVariable Long id) {
        return shiftService.getById(id);
    }

    @PostMapping
    public Shift create(@RequestBody Shift shift) {
        return shiftService.create(shift);
    }

    @PutMapping("/{id}")
    public Shift update(
            @PathVariable Long id,
            @RequestBody Shift shift
    ) {
        return shiftService.update(id, shift);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        shiftService.delete(id);
    }
}

package com.example.hrm.controller;

import com.example.hrm.entity.Position;
import com.example.hrm.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/positions")
@RequiredArgsConstructor
public class PositionController {

    private final PositionService positionService;

    // =========================
    // Get all positions
    // =========================
    @GetMapping
    public List<Position> getAll() {
        return positionService.getAll();
    }

    // =========================
    // Get position by id
    // =========================
    @GetMapping("/{id}")
    public Position getById(@PathVariable Integer id) {
        return positionService.getById(id);
    }

    // =========================
    // Create position
    // =========================
    @PostMapping
    public Position create(@RequestBody Position position) {
        return positionService.create(position);
    }

    // =========================
    // Update position
    // =========================
    @PutMapping("/{id}")
    public Position update(
            @PathVariable Integer id,
            @RequestBody Position position
    ) {
        return positionService.update(id, position);
    }

    // =========================
    // Delete position
    // =========================
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        positionService.delete(id);
    }
}
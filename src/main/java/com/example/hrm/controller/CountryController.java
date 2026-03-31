package com.example.hrm.controller;

import com.example.hrm.entity.Country;
import com.example.hrm.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    // =========================
    // Get all countries
    // =========================
    @GetMapping
    public List<Country> getAll() {
        return countryService.getAll();
    }

    // =========================
    // Get country by id
    // =========================
    @GetMapping("/{id}")
    public Country getById(@PathVariable Integer id) {
        return countryService.getById(id);
    }

    // =========================
    // Create country
    // =========================
    @PostMapping
    public Country create(@RequestBody Country country) {
        return countryService.create(country);
    }

    // =========================
    // Update country
    // =========================
    @PutMapping("/{id}")
    public Country update(
            @PathVariable Integer id,
            @RequestBody Country country
    ) {
        return countryService.update(id, country);
    }

    // =========================
    // Delete country
    // =========================
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        countryService.delete(id);
    }
}
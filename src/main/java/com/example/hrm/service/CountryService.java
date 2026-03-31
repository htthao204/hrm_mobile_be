package com.example.hrm.service;

import com.example.hrm.entity.Country;
import java.util.List;

public interface CountryService {
    List<Country> getAll();

    Country getById(Integer id);

    Country create(Country country);

    Country update(Integer id, Country country);

    void delete(Integer id);
}
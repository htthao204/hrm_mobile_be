package com.example.hrm.service.impl;

import com.example.hrm.entity.Country;
import com.example.hrm.repository.CountryRepository;
import com.example.hrm.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepo;

    @Override
    public List<Country> getAll() {
        return countryRepo.findAll();
    }

    @Override
    public Country getById(Integer id) {
        return countryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy quốc gia với ID: " + id));
    }

    @Override
    public Country create(Country country) {
        return countryRepo.save(country);
    }

    @Override
    public Country update(Integer id, Country countryDetails) {
        Country country = getById(id);

        // Giả sử Country có các trường name và code
        country.setName(countryDetails.getName());
        // country.setCode(countryDetails.getCode()); // Mở comment nếu entity có trường này

        return countryRepo.save(country);
    }

    @Override
    public void delete(Integer id) {
        Country country = getById(id);
        countryRepo.delete(country);
    }
}
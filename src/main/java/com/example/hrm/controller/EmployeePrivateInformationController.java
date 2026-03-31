package com.example.hrm.controller;

import com.example.hrm.dto.request.EmployeePrivateInformationRequest;
import com.example.hrm.entity.EmployeePrivateInformation;
import com.example.hrm.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee-private")
@RequiredArgsConstructor
public class EmployeePrivateInformationController {

    private final EmployeePrivateInformationRepository privateRepo;
    private final EmployeeInformationRepository employeeRepo;
    private final CountryRepository countryRepo;

    @PostMapping
    public EmployeePrivateInformation create(
            @RequestBody EmployeePrivateInformationRequest request) {

        EmployeePrivateInformation info = new EmployeePrivateInformation();

        info.setEmployee(
                employeeRepo.findById(request.getEmployeeId()).orElseThrow());

        info.setDateOfBirth(request.getDateOfBirth());
        info.setGender(request.getGender());
        info.setNationalId(request.getNationalId());
        info.setAddress(request.getAddress());

        info.setCountry(
                countryRepo.findById(request.getCountryId()).orElseThrow());

        return privateRepo.save(info);
    }

    @GetMapping("/employee/{employeeId}")
    public EmployeePrivateInformation getByEmployee(
            @PathVariable Integer employeeId) {

        return privateRepo.findByEmployee_Id(employeeId)
                .orElseThrow(() -> new RuntimeException("Private info not found"));
    }
}
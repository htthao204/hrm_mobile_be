package com.example.hrm.service.impl;

import com.example.hrm.entity.EmployeeInformation;
import com.example.hrm.entity.EmployeePrivateInformation;
import com.example.hrm.repository.EmployeeInformationRepository;
import com.example.hrm.repository.EmployeePrivateInformationRepository;
import com.example.hrm.service.EmployeePrivateInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
public class EmployeePrivateInformationServiceImpl
        implements EmployeePrivateInformationService {

    private final EmployeePrivateInformationRepository privateRepo;
    private final EmployeeInformationRepository employeeRepo;

    @Override
    public EmployeePrivateInformation getByEmployeeId(Integer employeeId) {

        return privateRepo.findByEmployee_Id(employeeId)
                .orElseThrow(() ->
                        new RuntimeException("Private info not found"));
    }

    @Override
    @Transactional
    public EmployeePrivateInformation create(
            Integer employeeId,
            EmployeePrivateInformation info
    ) {

        EmployeeInformation employee =
                employeeRepo.findById(employeeId)
                        .orElseThrow(() ->
                                new RuntimeException("Employee not found"));

        info.setEmployee(employee);

        return privateRepo.save(info);
    }

    @Override
    @Transactional
    public EmployeePrivateInformation update(
            Integer employeeId,
            EmployeePrivateInformation newInfo
    ) {

        EmployeePrivateInformation old =
                getByEmployeeId(employeeId);

        old.setDateOfBirth(newInfo.getDateOfBirth());
        old.setGender(newInfo.getGender());
        old.setNationalId(newInfo.getNationalId());
        old.setCountry(newInfo.getCountry());
        old.setAddress(newInfo.getAddress());

        return privateRepo.save(old);
    }

    @Override
    @Transactional
    public void delete(Integer employeeId) {

        privateRepo.delete(getByEmployeeId(employeeId));
    }
}
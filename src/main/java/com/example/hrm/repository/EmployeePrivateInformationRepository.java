package com.example.hrm.repository;

import com.example.hrm.entity.EmployeePrivateInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeePrivateInformationRepository
        extends JpaRepository<EmployeePrivateInformation, Integer> {

    Optional<EmployeePrivateInformation> findByEmployee_Id(Integer employeeId);
}
package com.example.hrm.repository;

import com.example.hrm.entity.EmployeeInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeInformationRepository
        extends JpaRepository<EmployeeInformation, Integer> {

    Optional<EmployeeInformation> findByEmail(String email);

    List<EmployeeInformation> findByDepartment_Id(Integer departmentId);
}

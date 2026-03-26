package com.example.hrm.repository;

import com.example.hrm.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave, Integer> {

    List<Leave> findByEmployee_Id(Integer employeeId);
}

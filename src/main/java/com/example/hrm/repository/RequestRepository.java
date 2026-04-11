package com.example.hrm.repository;

import com.example.hrm.entity.Request;
import com.example.hrm.entity.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RequestRepository
        extends JpaRepository<Request, Long> {

    List<Request> findByEmployee_Id(Long employeeId);

    List<Request> findByStatus(RequestStatus status);

    // ⭐ IMPORTANT
    List<Request> findByEmployee_IdAndStatusAndStartDateBetween(
            Long employeeId,
            RequestStatus status,
            LocalDate from,
            LocalDate to
    );
}
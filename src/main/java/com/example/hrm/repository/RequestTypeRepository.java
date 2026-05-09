package com.example.hrm.repository;

import com.example.hrm.entity.RequestType;
import com.example.hrm.entity.RequestTypeCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RequestTypeRepository extends JpaRepository<RequestType, Integer> {

    boolean existsByCode(RequestTypeCode code);

    boolean existsByName(String name);

    Optional<RequestType> findByCode(RequestTypeCode code);
}
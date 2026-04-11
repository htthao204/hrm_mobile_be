package com.example.hrm.repository;

import com.example.hrm.entity.Position;
import com.example.hrm.entity.RequestType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestTypeRepository extends JpaRepository<RequestType, Integer>{
}
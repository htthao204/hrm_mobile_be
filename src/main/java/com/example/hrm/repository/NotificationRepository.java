package com.example.hrm.repository;

import com.example.hrm.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository
        extends JpaRepository<Notification, Integer> {

    List<Notification> findByEmployee_IdOrderByCreatedAtDesc(Integer employeeId);

    Integer countByEmployee_IdAndIsReadFalse(Integer employeeId);
    List<Notification> findAllByOrderByCreatedAtDesc();
}
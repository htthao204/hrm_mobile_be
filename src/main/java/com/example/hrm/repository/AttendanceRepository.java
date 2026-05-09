package com.example.hrm.repository;

import com.example.hrm.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    // lấy toàn bộ lịch sử
    List<Attendance> findByEmployee_Id(Integer employeeId);

    // 🔥 QUAN TRỌNG: lấy theo ngày
    Optional<Attendance> findByEmployee_IdAndDate(Integer employeeId, LocalDate date);

    // lấy khoảng thời gian (dùng cho report tháng)
    List<Attendance> findByEmployee_IdAndDateBetween(
            Integer employeeId,
            LocalDate startDate,
            LocalDate endDate
    );
}
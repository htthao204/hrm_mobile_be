package com.example.hrm.entity;

import com.example.hrm.type.AttendanceAction;
import com.example.hrm.type.AttendanceSource;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Table(name = "attendance_logs")
@Getter
@Setter
@NoArgsConstructor // Nên có constructor không tham số cho JPA
@AllArgsConstructor // Tiện cho việc khởi tạo nhanh
public class AttendanceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY) // Thường dùng Lazy để tối ưu hiệu năng
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeInformation employee;

    @Column(name = "log_time", nullable = false)
    private LocalDateTime logTime;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private AttendanceAction action;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private AttendanceSource source;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;
}
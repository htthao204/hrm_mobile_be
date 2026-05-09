package com.example.hrm.service.impl;

import com.example.hrm.config.CompanyLocationConfig;
import com.example.hrm.dto.request.AttendanceLogRequest;
import com.example.hrm.dto.response.AttendanceLogResponse;
import com.example.hrm.entity.AttendanceLog;
import com.example.hrm.entity.EmployeeInformation;
import com.example.hrm.repository.AttendanceLogRepository;

import com.example.hrm.repository.EmployeeInformationRepository;
import com.example.hrm.service.AttendanceLogService;
import com.example.hrm.service.CloudinaryService;
import com.example.hrm.service.FaceRecognitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceLogServiceImpl implements AttendanceLogService {

    private final AttendanceLogRepository attendanceLogRepository;
    private final EmployeeInformationRepository employeeRepository;
    private final CloudinaryService cloudinaryService;
    private final CompanyLocationConfig companyLocationConfig;
    private final FaceRecognitionService faceRecognitionService;

    @Override
    public AttendanceLogResponse createLog(
            AttendanceLogRequest request,
            MultipartFile image
    ) {

        // ================= VERIFY GPS =================
        double distance = calculateDistance(
                request.getLatitude(),
                request.getLongitude(),
                companyLocationConfig.getLatitude(),
                companyLocationConfig.getLongitude()
        );

        if (distance > companyLocationConfig.getAllowedRadiusMeter()) {
            throw new RuntimeException(
                    "Bạn không ở trong phạm vi công ty"
            );
        }

        // ================= GET EMPLOYEE =================
        EmployeeInformation employee =
                employeeRepository.findById(
                        request.getEmployeeId()
                ).orElseThrow(() ->
                        new RuntimeException("Không tìm thấy nhân viên")
                );

        if (employee.getFaceImageUrl() == null) {
            throw new RuntimeException(
                    "Nhân viên chưa tải ảnh khuôn mặt. Vui lòng tải ảnh lên"
            );
        }

        boolean matched =
                faceRecognitionService.verifyFace(
                        employee.getFaceImageUrl(),
                        image
                );

        if (!matched) {
            throw new RuntimeException(
                    "Khuôn mặt không khớp"
            );
        }


        String imageUrl =
                cloudinaryService.uploadImage(image);

        AttendanceLog log = new AttendanceLog();

        log.setEmployee(employee);

        log.setAction(request.getAction());

        log.setSource(request.getSource());

        log.setLatitude(request.getLatitude());

        log.setLongitude(request.getLongitude());

        log.setImageUrl(imageUrl);

        log.setLogTime(
                request.getLogTime() != null
                        ? request.getLogTime()
                        : LocalDateTime.now()
        );

        AttendanceLog saved =
                attendanceLogRepository.save(log);

        return mapToResponse(saved);
    }

    private double calculateDistance(
            double lat1,
            double lon1,
            double lat2,
            double lon2
    ) {

        double earthRadius = 6371000;

        double dLat =
                Math.toRadians(lat2 - lat1);

        double dLon =
                Math.toRadians(lon2 - lon1);

        double a =
                Math.sin(dLat / 2)
                        * Math.sin(dLat / 2)
                        + Math.cos(Math.toRadians(lat1))
                        * Math.cos(Math.toRadians(lat2))
                        * Math.sin(dLon / 2)
                        * Math.sin(dLon / 2);

        double c =
                2 * Math.atan2(
                        Math.sqrt(a),
                        Math.sqrt(1 - a)
                );

        return earthRadius * c;
    }


    @Override
    public List<AttendanceLogResponse> getAllLogs() {

        return attendanceLogRepository
                .findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    @Override
    public List<AttendanceLogResponse>
    getLogsByEmployee(Integer employeeId) {

        return attendanceLogRepository
                .findByEmployee_Id(employeeId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    @Override
    public void deleteLog(Integer id) {

        attendanceLogRepository.deleteById(id);
    }

    // ================= MAPPER =================
    private AttendanceLogResponse
    mapToResponse(AttendanceLog log) {

        return AttendanceLogResponse.builder()
                .id(log.getId())
                .employeeId(log.getEmployee().getId())
                .employeeName(
                        log.getEmployee().getFullName()
                )
                .logTime(log.getLogTime())
                .action(log.getAction().name())
                .source(log.getSource().name())
                .imageUrl(log.getImageUrl())
                .latitude(log.getLatitude())
                .longitude(log.getLongitude())
                .build();
    }
}
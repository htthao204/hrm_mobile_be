package com.example.hrm.service.impl;

import com.example.hrm.dto.NotificationDTO;
import com.example.hrm.entity.EmployeeInformation;
import com.example.hrm.entity.Notification;
import com.example.hrm.entity.NotificationType;
import com.example.hrm.mapper.NotificationMapper;
import com.example.hrm.repository.EmployeeInformationRepository;
import com.example.hrm.repository.NotificationRepository;
import com.example.hrm.service.FCMService;
import com.example.hrm.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final EmployeeInformationRepository employeeRepository;
    private final FCMService fcmService;
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public List<NotificationDTO> getMyNotifications(Integer employeeId) {
        return NotificationMapper.toDTOList(
                notificationRepository
                        .findByEmployee_IdOrderByCreatedAtDesc(employeeId)
        );
    }
    @Override
    public void createNotification(
            Integer employeeId,
            String title,
            String message,
            NotificationType type
    ) {
        EmployeeInformation employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Notification notification = new Notification();
        notification.setEmployee(employee);
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setType(type);
        notification.setIsRead(false);

        notificationRepository.save(notification);

        // ✅ convert DTO
        NotificationDTO dto = NotificationMapper.toDTO(notification);

        // 🔥 gửi notification realtime
        messagingTemplate.convertAndSend(
                "/topic/notifications/" + employeeId,
                dto
        );

        // 🔥 gửi luôn unread count (badge 🔴)
        Integer unread = notificationRepository
                .countByEmployee_IdAndIsReadFalse(employeeId);

        messagingTemplate.convertAndSend(
                "/topic/notifications/" + employeeId + "/unread",
                unread
        );
    }
    @Override
    public void markAsRead(Integer notificationId) {

        Notification notification =
                notificationRepository.findById(notificationId)
                        .orElseThrow();

        notification.setIsRead(true);
        notificationRepository.save(notification);

        Integer employeeId = notification.getEmployee().getId();

        // 🔥 update unread realtime
        Integer unread = notificationRepository
                .countByEmployee_IdAndIsReadFalse(employeeId);

        messagingTemplate.convertAndSend(
                "/topic/notifications/" + employeeId + "/unread",
                unread
        );
    }

    @Override
    public Integer countUnread(Integer employeeId) {
        return notificationRepository
                .countByEmployee_IdAndIsReadFalse(employeeId);
    }
    @Override
    public List<NotificationDTO> getAllNotifications() {
        return NotificationMapper.toDTOList(
                notificationRepository.findAllByOrderByCreatedAtDesc()
        );
    }

}
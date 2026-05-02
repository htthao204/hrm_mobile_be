package com.example.hrm.service;

import com.example.hrm.dto.NotificationDTO;
import com.example.hrm.entity.Notification;
import com.example.hrm.entity.NotificationType;

import java.util.List;

public interface NotificationService {

    List<NotificationDTO> getMyNotifications(Integer employeeId);

    void markAsRead(Integer notificationId);

    Integer countUnread(Integer employeeId);

    void createNotification(
            Integer employeeId,
            String title,
            String message,
            NotificationType type
    );

    List<NotificationDTO> getAllNotifications();
}
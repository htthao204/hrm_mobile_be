package com.example.hrm.event;

import com.example.hrm.entity.NotificationType;

public record NotificationEvent(
        Integer employeeId,
        String title,
        String message,
        NotificationType type
) {}
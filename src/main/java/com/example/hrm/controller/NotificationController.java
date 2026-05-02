package com.example.hrm.controller;

import com.example.hrm.dto.NotificationDTO;
import com.example.hrm.entity.Notification;
import com.example.hrm.entity.NotificationType;
import com.example.hrm.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    // 🔥 get list notification
    @GetMapping("/{employeeId}")
    public List<NotificationDTO> getNotifications(
            @PathVariable Integer employeeId
    ) {
        return notificationService.getMyNotifications(employeeId);
    }

    // 🔥 mark read
    @PutMapping("/{id}/read")
    public void markRead(@PathVariable Integer id) {
        notificationService.markAsRead(id);
    }

    // 🔥 unread count (badge 🔴)
    @GetMapping("/{employeeId}/unread-count")
    public Integer unreadCount(
            @PathVariable Integer employeeId
    ) {
        return notificationService.countUnread(employeeId);
    }

    @PostMapping("/test")
    public void test() {
        System.out.println("API TEST CALLED");
        notificationService.createNotification(
                9,
                "Test realtime",
                "Hello WebSocket",
                NotificationType.INFO
        );
    }

    @GetMapping()
    public List<NotificationDTO> getAll() {
        return notificationService.getAllNotifications();
    }
}
package com.example.hrm.event;


import com.example.hrm.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationListener {

    private final NotificationService notificationService;

    @EventListener
    public void handle(NotificationEvent event) {

        notificationService.createNotification(
                event.employeeId(),
                event.title(),
                event.message(),
                event.type()
        );
    }
}

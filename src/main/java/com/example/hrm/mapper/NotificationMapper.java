package com.example.hrm.mapper;

import com.example.hrm.dto.NotificationDTO;
import com.example.hrm.entity.Notification;

import java.util.List;
import java.util.stream.Collectors;

public class NotificationMapper {

    public static NotificationDTO toDTO(Notification entity) {
        if (entity == null) return null;

        return new NotificationDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getMessage(),
                entity.getType(),
                entity.getIsRead(),
                entity.getCreatedAt()
        );
    }

    public static List<NotificationDTO> toDTOList(List<Notification> list) {
        return list.stream()
                .map(NotificationMapper::toDTO)
                .collect(Collectors.toList());
    }
}
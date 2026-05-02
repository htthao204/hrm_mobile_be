package com.example.hrm.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HolidayTimelineDto {

    private Integer id;

    private LocalDateTime time;

    private String title;

    private String description;
}

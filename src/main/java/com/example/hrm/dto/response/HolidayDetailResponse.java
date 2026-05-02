package com.example.hrm.dto.response;

import com.example.hrm.dto.HolidayTimelineDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class HolidayDetailResponse {

    private Integer id;
    private String name;

    private LocalDate startDate;
    private LocalDate endDate;

    private Boolean isGlobal;
    private Boolean isPaid;

    private String description;
    private String type;

    private List<HolidayTimelineDto> timelines;
}
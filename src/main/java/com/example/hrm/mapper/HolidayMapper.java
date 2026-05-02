package com.example.hrm.mapper;

import com.example.hrm.dto.HolidayTimelineDto;
import com.example.hrm.dto.response.HolidayDetailResponse;
import com.example.hrm.entity.Holiday;
import com.example.hrm.entity.HolidayTimeline;
import org.springframework.stereotype.Component;

@Component
public class HolidayMapper {

    // ✅ PUBLIC để service gọi
    public HolidayDetailResponse mapHoliday(Holiday holiday) {

        HolidayDetailResponse res = new HolidayDetailResponse();

        res.setId(holiday.getId());
        res.setName(holiday.getName());
        res.setStartDate(holiday.getStartDate());
        res.setEndDate(holiday.getEndDate());
        res.setIsGlobal(holiday.getIsGlobal());
        res.setIsPaid(holiday.getIsPaid());
        res.setDescription(holiday.getDescription());
        res.setType(holiday.getType().name());

        if (holiday.getTimelines() != null) {
            res.setTimelines(
                    holiday.getTimelines()
                            .stream()
                            .map(this::mapTimeline)
                            .toList()
            );
        }

        return res;
    }

    private HolidayTimelineDto mapTimeline(HolidayTimeline t) {

        HolidayTimelineDto dto = new HolidayTimelineDto();

        dto.setId(t.getId());
        dto.setTime(t.getTime());
        dto.setTitle(t.getTitle());
        dto.setDescription(t.getDescription());

        return dto;
    }
}
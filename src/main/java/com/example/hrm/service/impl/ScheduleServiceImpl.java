package com.example.hrm.service.impl;

import com.example.hrm.dto.ScheduleItemDTO;
import com.example.hrm.entity.RequestStatus;
import com.example.hrm.repository.EmployeeShiftRepository;
import com.example.hrm.repository.HolidayRepository;
import com.example.hrm.repository.RequestRepository;
import com.example.hrm.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final EmployeeShiftRepository employeeShiftRepo;
    private final HolidayRepository holidayRepo;
    private final RequestRepository requestRepo;

    @Override
    public List<ScheduleItemDTO> getUpcomingSchedule(
            Long employeeId,
            LocalDate from,
            LocalDate to
    ) {

        List<ScheduleItemDTO> result = new ArrayList<>();

        // ✅ SHIFT
        employeeShiftRepo
                .findByEmployee_IdAndWorkDateBetween(
                        employeeId.intValue(),
                        from,
                        to
                )
                .forEach(es -> {

                    ScheduleItemDTO dto = new ScheduleItemDTO();
                    dto.setType("SHIFT");
                    dto.setTitle(es.getShift().getName());
                    dto.setDate(es.getWorkDate());
                    dto.setStartTime(es.getShift().getStartTime());
                    dto.setEndTime(es.getShift().getEndTime());

                    result.add(dto);
                });

        // ✅ HOLIDAY (RANGE SUPPORT)
        holidayRepo
                .findByStartDateLessThanEqualAndEndDateGreaterThanEqual(to, from)
                .forEach(h -> {

                    LocalDate current = h.getStartDate();

                    while (!current.isAfter(h.getEndDate())) {

                        // chỉ lấy ngày nằm trong range query
                        if (!current.isBefore(from) && !current.isAfter(to)) {

                            ScheduleItemDTO dto = new ScheduleItemDTO();
                            dto.setType("HOLIDAY");
                            dto.setTitle(h.getName());
                            dto.setDate(current);

                            result.add(dto);
                        }

                        current = current.plusDays(1);
                    }
                });

        // ✅ APPROVED REQUEST
        requestRepo
                .findByEmployee_IdAndStatusAndStartDateBetween(
                        employeeId,
                        RequestStatus.APPROVED,
                        from,
                        to
                )
                .forEach(r -> {

                    ScheduleItemDTO dto = new ScheduleItemDTO();
                    dto.setType(r.getRequestType().getCode().name());
                    dto.setTitle(r.getRequestType().getName());
                    dto.setDate(r.getStartDate());
                    dto.setStartTime(r.getStartTime());
                    dto.setEndTime(r.getEndTime());

                    result.add(dto);
                });

        return result;
    }
}
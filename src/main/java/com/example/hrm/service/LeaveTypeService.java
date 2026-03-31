package com.example.hrm.service;


import com.example.hrm.entity.LeaveType;
import java.util.List;

public interface LeaveTypeService {
    List<LeaveType> getAll();

    LeaveType getById(Integer id);

    LeaveType create(LeaveType leaveType);

    LeaveType update(Integer id, LeaveType leaveType);

    void delete(Integer id);
}

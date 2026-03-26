package com.example.hrm.service;

import com.example.hrm.entity.Leave;

import java.util.List;

public interface LeaveService {

    Leave createLeave(Leave leave);

    List<Leave> getByEmployee(Integer employeeId);

    Leave approve(Integer leaveId);

    Leave reject(Integer leaveId);
}
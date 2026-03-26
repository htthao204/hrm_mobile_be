package com.example.hrm.service;

import com.example.hrm.entity.Leave;
import com.example.hrm.repository.LeaveRepository;
import com.example.hrm.type.LeaveStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveServiceImpl implements LeaveService {

    private final LeaveRepository leaveRepo;

    @Override
    public Leave createLeave(Leave leave) {
        leave.setStatus(LeaveStatus.Pending);
        return leaveRepo.save(leave);
    }

    @Override
    public List<Leave> getByEmployee(Integer employeeId) {
        return leaveRepo.findByEmployee_Id(employeeId);
    }

    @Override
    public Leave approve(Integer leaveId) {
        Leave leave = leaveRepo.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        leave.setStatus(LeaveStatus.Approved);
        return leaveRepo.save(leave);
    }

    @Override
    public Leave reject(Integer leaveId) {
        Leave leave = leaveRepo.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        leave.setStatus(LeaveStatus.Rejected);
        return leaveRepo.save(leave);
    }
}

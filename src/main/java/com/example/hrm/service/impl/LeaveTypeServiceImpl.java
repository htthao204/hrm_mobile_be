package com.example.hrm.service.impl;

import com.example.hrm.entity.LeaveType;
import com.example.hrm.repository.LeaveTypeRepository;
import com.example.hrm.service.LeaveTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveTypeServiceImpl implements LeaveTypeService {

    private final LeaveTypeRepository leaveTypeRepo;

    @Override
    public List<LeaveType> getAll() {
        return leaveTypeRepo.findAll();
    }

    @Override
    public LeaveType getById(Integer id) {
        return leaveTypeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy loại nghỉ phép với ID: " + id));
    }

    @Override
    public LeaveType create(LeaveType leaveType) {
        // Có thể thêm logic kiểm tra trùng tên loại nghỉ phép ở đây
        return leaveTypeRepo.save(leaveType);
    }

    @Override
    public LeaveType update(Integer id, LeaveType leaveTypeDetails) {
        LeaveType leaveType = getById(id);

        // Cập nhật các thông tin của loại nghỉ phép
        leaveType.setName(leaveTypeDetails.getName());
        // leaveType.setDaysAllowed(leaveTypeDetails.getDaysAllowed()); // Nếu có giới hạn số ngày nghỉ
        // leaveType.setPaid(leaveTypeDetails.getIsPaid()); // Nếu có phân biệt nghỉ hưởng lương/không lương

        return leaveTypeRepo.save(leaveType);
    }

    @Override
    public void delete(Integer id) {
        LeaveType leaveType = getById(id);
        // Lưu ý: Trong thực tế nên kiểm tra xem có LeaveRequest nào đang sử dụng loại này không trước khi xóa
        leaveTypeRepo.delete(leaveType);
    }
}
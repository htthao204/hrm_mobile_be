package com.example.hrm.service.impl;

import com.example.hrm.entity.Position;
import com.example.hrm.repository.PositionRepository;
import com.example.hrm.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepo;

    @Override
    public List<Position> getAll() {
        return positionRepo.findAll();
    }

    @Override
    public Position getById(Integer id) {
        return positionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Chức vụ không tồn tại với ID: " + id));
    }

    @Override
    public Position create(Position position) {
        // Bạn có thể thêm kiểm tra nếu chức vụ đã tồn tại dựa trên tên hoặc mã
        return positionRepo.save(position);
    }

    @Override
    public Position update(Integer id, Position positionDetails) {
        Position position = getById(id);

        // Cập nhật các thông tin cơ bản của chức vụ
        position.setName(positionDetails.getName());
        // position.setBaseSalary(positionDetails.getBaseSalary()); // Nếu có lương cơ bản theo chức vụ
        // position.setNote(positionDetails.getNote()); // Nếu có trường ghi chú

        return positionRepo.save(position);
    }

    @Override
    public void delete(Integer id) {
        Position position = getById(id);
        positionRepo.delete(position);
    }
}
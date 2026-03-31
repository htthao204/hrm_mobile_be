package com.example.hrm.service;

import com.example.hrm.entity.Position;
import java.util.List;

public interface PositionService {
    List<Position> getAll();

    Position getById(Integer id);

    Position create(Position position);

    Position update(Integer id, Position position);

    void delete(Integer id);
}
package com.example.hrm.service.impl;

import com.example.hrm.entity.Request;
import com.example.hrm.repository.RequestRepository;
import com.example.hrm.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;

    @Override
    public Request create(Request request) {
        request.setCreatedAt(LocalDateTime.now());
        request.setUpdatedAt(LocalDateTime.now());
        return requestRepository.save(request);
    }

    @Override
    public Request update(Long id, Request request) {

        Request old = requestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        old.setStartDate(request.getStartDate());
        old.setEndDate(request.getEndDate());
        old.setStartTime(request.getStartTime());
        old.setEndTime(request.getEndTime());
        old.setReason(request.getReason());
        old.setMetadata(request.getMetadata());
        old.setStatus(request.getStatus());

        old.setUpdatedAt(LocalDateTime.now());

        return requestRepository.save(old);
    }

    @Override
    public void delete(Long id) {
        requestRepository.deleteById(id);
    }

    @Override
    public Request getById(Long id) {
        return requestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
    }

    @Override
    public List<Request> getAll() {
        return requestRepository.findAll();
    }
}
package com.example.hrm.service.impl;

import com.example.hrm.dto.request.RequestCreateRequest;
import com.example.hrm.dto.response.RequestResponse;
import com.example.hrm.entity.EmployeeInformation;
import com.example.hrm.entity.Request;
import com.example.hrm.entity.RequestStatus;
import com.example.hrm.entity.RequestType;
import com.example.hrm.mapper.RequestMapper;
import com.example.hrm.repository.EmployeeInformationRepository;
import com.example.hrm.repository.RequestRepository;
import com.example.hrm.repository.RequestTypeRepository;
import com.example.hrm.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;
    private final EmployeeInformationRepository employeeRepo;
    private final RequestTypeRepository requestTypeRepo;

    @Override
    public RequestResponse create(RequestCreateRequest req) {

        EmployeeInformation emp = employeeRepo.findById(req.employeeId)
                .orElseThrow();

        RequestType type = requestTypeRepo.findById(req.requestTypeId)
                .orElseThrow();

        Request r = new Request();
        r.setEmployee(emp);
        r.setRequestType(type);

        r.setStartDate(req.startDate);
        r.setEndDate(req.endDate);
        r.setStartTime(req.startTime);
        r.setEndTime(req.endTime);
        r.setReason(req.reason);
        r.setMetadata(req.metadata);

        r.setStatus(RequestStatus.PENDING);
        r.setCreatedAt(LocalDateTime.now());
        r.setUpdatedAt(LocalDateTime.now());

        return requestMapper.toDto(requestRepository.save(r));
    }

    @Override
    public RequestResponse getById(Long id) {
        Request r = requestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        return requestMapper.toDto(r);
    }

    @Override
    public List<RequestResponse> getAll() {
        return requestRepository.findAll()
                .stream()
                .map(requestMapper::toDto)
                .toList();
    }

    @Override
    public RequestResponse update(Long id, Request request) {

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

        return requestMapper.toDto(requestRepository.save(old));
    }

    @Override
    public void delete(Long id) {
        requestRepository.deleteById(id);
    }
}
package com.example.hrm.service.impl;

import com.example.hrm.dto.request.RequestCreateRequest;
import com.example.hrm.dto.request.RequestUpdateRequest;
import com.example.hrm.dto.response.RequestResponse;
import com.example.hrm.entity.*;
import com.example.hrm.mapper.RequestMapper;
import com.example.hrm.repository.EmployeeInformationRepository;
import com.example.hrm.repository.RequestRepository;
import com.example.hrm.repository.RequestTypeRepository;
import com.example.hrm.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.hrm.security.SecurityUtil;
import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;
    private final EmployeeInformationRepository employeeRepo;
    private final RequestTypeRepository requestTypeRepo;

    // ================= CREATE =================
    @Override
    public RequestResponse create(RequestCreateRequest req) {

        EmployeeInformation emp =
                employeeRepo.findById(req.getEmployeeId())
                        .orElseThrow(() ->
                                new RuntimeException("Employee not found"));

        // 🔥 convert String -> ENUM
        RequestTypeCode code =
                RequestTypeCode.valueOf(req.getRequestTypeCode());

        RequestType type =
                requestTypeRepo.findByCode(code)
                        .orElseThrow(() ->
                                new RuntimeException("RequestType not found"));

        Request r = new Request();
        r.setEmployee(emp);
        r.setRequestType(type);

        r.setStartDate(req.getStartDate());
        r.setEndDate(req.getEndDate());
        r.setStartTime(req.getStartTime());
        r.setEndTime(req.getEndTime());
        r.setReason(req.getReason());
        r.setMetadata(req.getMetadata());

        r.setStatus(RequestStatus.PENDING);
        r.setCreatedAt(LocalDateTime.now());
        r.setUpdatedAt(LocalDateTime.now());

        return requestMapper.toDto(requestRepository.save(r));
    }
    @Override
    public List<RequestResponse> getByEmployeeId(Integer employeeId) {

        return requestRepository
                .findByEmployee_Id(employeeId)
                .stream()
                .map(requestMapper::toDto)
                .toList();
    }
    // ================= GET =================
    @Override
    public RequestResponse getById(Long id) {
        Request r = requestRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Request not found"));

        return requestMapper.toDto(r);
    }

    @Override
    public List<RequestResponse> getAll() {
        return requestRepository.findAll()
                .stream()
                .map(requestMapper::toDto)
                .toList();
    }

    // ================= UPDATE =================
    @Override
    public RequestResponse update(Long id, RequestUpdateRequest req) {

        Request old = requestRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Request not found"));

        old.setStartDate(req.getStartDate());
        old.setEndDate(req.getEndDate());
        old.setStartTime(req.getStartTime());
        old.setEndTime(req.getEndTime());
        old.setReason(req.getReason());
        old.setMetadata(req.getMetadata());
        old.setUpdatedAt(LocalDateTime.now());

        return requestMapper.toDto(requestRepository.save(old));
    }

    // ================= DELETE =================
    @Override
    public void delete(Long id) {
        if (!requestRepository.existsById(id)) {
            throw new RuntimeException("Request not found");
        }
        requestRepository.deleteById(id);
    }
}
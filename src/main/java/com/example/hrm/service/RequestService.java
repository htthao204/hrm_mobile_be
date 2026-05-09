package com.example.hrm.service;

import com.example.hrm.dto.request.RequestCreateRequest;
import com.example.hrm.dto.request.RequestUpdateRequest;
import com.example.hrm.dto.response.RequestResponse;

import java.util.List;

public interface RequestService {

    RequestResponse create(RequestCreateRequest request);

    RequestResponse update(Long id, RequestUpdateRequest request);

    void delete(Long id);
    List<RequestResponse> getByEmployeeId(Integer employeeId);
    RequestResponse getById(Long id);

    List<RequestResponse> getAll();
}
package com.example.hrm.service;

import com.example.hrm.dto.request.RequestCreateRequest;
import com.example.hrm.dto.response.RequestResponse;
import com.example.hrm.entity.Request;

import java.util.List;

public interface RequestService {

    RequestResponse create(RequestCreateRequest request);

    RequestResponse update(Long id, Request request);

    void delete(Long id);

    RequestResponse getById(Long id);

    List<RequestResponse> getAll();

}
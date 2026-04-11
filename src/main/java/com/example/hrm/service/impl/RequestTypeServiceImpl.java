package com.example.hrm.service.impl;


import com.example.hrm.entity.RequestType;
import com.example.hrm.repository.RequestTypeRepository;
import com.example.hrm.service.RequestTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestTypeServiceImpl implements RequestTypeService {

    private final RequestTypeRepository requestTypeRepository;

    public RequestTypeServiceImpl(RequestTypeRepository requestTypeRepository) {
        this.requestTypeRepository = requestTypeRepository;
    }

    @Override
    public List<RequestType> getAll() {
        return requestTypeRepository.findAll();
    }

    @Override
    public RequestType getById(Integer id) {
        return requestTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RequestType not found"));
    }

    @Override
    public RequestType create(RequestType requestType) {
        return requestTypeRepository.save(requestType);
    }

    @Override
    public RequestType update(Integer id, RequestType requestType) {

        RequestType existing = getById(id);

        existing.setCode(requestType.getCode());
        existing.setName(requestType.getName());

        return requestTypeRepository.save(existing);
    }

    @Override
    public void delete(Integer id) {
        requestTypeRepository.deleteById(id);
    }
}
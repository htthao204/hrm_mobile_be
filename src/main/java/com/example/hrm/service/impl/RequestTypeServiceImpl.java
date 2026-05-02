package com.example.hrm.service.impl;


import com.example.hrm.entity.RequestType;
import com.example.hrm.entity.RequestTypeCode;
import com.example.hrm.repository.RequestTypeRepository;
import com.example.hrm.service.RequestTypeService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public List<RequestType> createList(List<RequestType> requestTypes) {

        Set<RequestTypeCode> codes = new HashSet<>();

        for (RequestType rt : requestTypes) {

            // check duplicate trong request
            if (!codes.add(rt.getCode())) {
                throw new RuntimeException("Duplicate code in request: " + rt.getCode());
            }

            // check DB
            if (requestTypeRepository.existsByCode(rt.getCode())) {
                throw new RuntimeException("Code already exists: " + rt.getCode());
            }

            if (requestTypeRepository.existsByName(rt.getName())) {
                throw new RuntimeException("Name already exists: " + rt.getName());
            }
        }

        return requestTypeRepository.saveAll(requestTypes);
    }
    @Override
    public RequestType create(RequestType requestType) {

        if (requestTypeRepository.existsByCode(requestType.getCode())) {
            throw new RuntimeException("Code already exists");
        }

        if (requestTypeRepository.existsByName(requestType.getName())) {
            throw new RuntimeException("Name already exists");
        }

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
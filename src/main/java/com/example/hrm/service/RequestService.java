package com.example.hrm.service;

import com.example.hrm.entity.Request;

import java.util.List;

public interface RequestService {

    Request create(Request request);

    Request update(Long id, Request request);

    void delete(Long id);

    Request getById(Long id);

    List<Request> getAll();

}
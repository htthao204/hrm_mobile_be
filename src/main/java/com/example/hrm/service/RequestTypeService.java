package com.example.hrm.service;

import com.example.hrm.entity.RequestType;
import java.util.List;
public interface RequestTypeService {

    List<RequestType> getAll();

    RequestType getById(Integer id);

    RequestType create(RequestType requestType);

    RequestType update(Integer id, RequestType requestType);

    void delete(Integer id);

    List<RequestType> createList(List<RequestType> requestTypes);
}
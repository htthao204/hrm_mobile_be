package com.example.hrm.controller;

import com.example.hrm.entity.RequestType;
import com.example.hrm.service.RequestTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/request-types")
@CrossOrigin
public class RequestTypeController {

    private final RequestTypeService requestTypeService;

    public RequestTypeController(RequestTypeService requestTypeService) {
        this.requestTypeService = requestTypeService;
    }

    // GET ALL
    @GetMapping
    public List<RequestType> getAll() {
        return requestTypeService.getAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public RequestType getById(@PathVariable Integer id) {
        return requestTypeService.getById(id);
    }

    // CREATE
    @PostMapping
    public RequestType create(@RequestBody RequestType requestType) {
        return requestTypeService.create(requestType);
    }

    // UPDATE
    @PutMapping("/{id}")
    public RequestType update(
            @PathVariable Integer id,
            @RequestBody RequestType requestType
    ) {
        return requestTypeService.update(id, requestType);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        requestTypeService.delete(id);
    }
}
package com.example.hrm.controller;

import com.example.hrm.dto.request.RequestCreateRequest;
import com.example.hrm.dto.response.RequestResponse;
import com.example.hrm.entity.Request;
import com.example.hrm.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    @PostMapping
    public RequestResponse create(@RequestBody RequestCreateRequest req) {
        return requestService.create(req);
    }

    @PutMapping("/{id}")
    public RequestResponse update(
            @PathVariable Long id,
            @RequestBody Request request
    ) {
        return requestService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        requestService.delete(id);
    }

    @GetMapping("/{id}")
    public RequestResponse getById(@PathVariable Long id) {
        return requestService.getById(id);
    }

    @GetMapping
    public List<RequestResponse> getAll() {
        return requestService.getAll();
    }
}
package com.example.hrm.controller;

import com.example.hrm.dto.request.RequestCreateRequest;
import com.example.hrm.dto.request.RequestUpdateRequest;
import com.example.hrm.dto.response.RequestResponse;
import com.example.hrm.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    // ================= CREATE =================
    @PostMapping
    public RequestResponse create(
            @RequestBody RequestCreateRequest req
    ) {
        return requestService.create(req);
    }

    // ================= UPDATE =================
    @PutMapping("/{id}")
    public RequestResponse update(
            @PathVariable Long id,
            @RequestBody RequestUpdateRequest req
    ) {
        return requestService.update(id, req);
    }

    // ================= DELETE =================
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        requestService.delete(id);
    }

    // ================= GET BY ID =================
    @GetMapping("/{id}")
    public RequestResponse getById(@PathVariable Long id) {
        return requestService.getById(id);
    }
    @GetMapping("/employee/{employeeId}")
    public List<RequestResponse> getByEmployee(
            @PathVariable Integer employeeId
    ) {
        return requestService.getByEmployeeId(employeeId);
    }
    // ================= GET ALL =================
    @GetMapping
    public List<RequestResponse> getAll() {
        return requestService.getAll();
    }
}
package com.example.hrm.controller;

import com.example.hrm.entity.Request;
import com.example.hrm.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    @PostMapping
    public Request create(@RequestBody Request request) {
        return requestService.create(request);
    }

    @PutMapping("/{id}")
    public Request update(
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
    public Request getById(@PathVariable Long id) {
        return requestService.getById(id);
    }

    @GetMapping
    public List<Request> getAll() {
        return requestService.getAll();
    }
}
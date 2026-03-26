package com.example.hrm.controller;

import com.example.hrm.entity.Leave;
import com.example.hrm.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leaves")
@RequiredArgsConstructor
public class LeaveController {

    private final LeaveService leaveService;

    @PostMapping
    public Leave create(@RequestBody Leave leave) {
        return leaveService.createLeave(leave);
    }

    @PostMapping("/approve/{id}")
    public Leave approve(@PathVariable Integer id) {
        return leaveService.approve(id);
    }

    @PostMapping("/reject/{id}")
    public Leave reject(@PathVariable Integer id) {
        return leaveService.reject(id);
    }
}

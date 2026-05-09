package com.example.hrm.controller;

import com.example.hrm.dto.request.EmployeeCreateRequest;
import com.example.hrm.dto.response.EmployeeResponse;
import com.example.hrm.dto.response.FaceStatusResponse;
import com.example.hrm.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeInformationController {

    private final EmployeeService employeeService;

    // CREATE
    @PostMapping(consumes = "multipart/form-data")
    public EmployeeResponse create(

            @RequestPart("request")
            EmployeeCreateRequest request,

            @RequestPart(
                    value = "avatar",
                    required = false
            )
            MultipartFile avatar
    ) {

        return employeeService.create(
                request,
                avatar
        );
    }

    // GET ALL
    @GetMapping
    public List<EmployeeResponse> getAll() {
        return employeeService.getAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public EmployeeResponse getById(@PathVariable Integer id) {
        return employeeService.getById(id);
    }

    // UPDATE
    @PutMapping(
            value = "/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public EmployeeResponse update(

            @PathVariable Integer id,

            @RequestPart("request")
            EmployeeCreateRequest request,

            @RequestPart(
                    value = "avatar",
                    required = false
            )
            MultipartFile avatar
    ) {

        return employeeService.update(
                id,
                request,
                avatar
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        employeeService.delete(id);
        return "Employee deleted successfully";
    }

    // FILTER
    @GetMapping("/department/{departmentId}")
    public List<EmployeeResponse> getByDepartment(
            @PathVariable Integer departmentId) {
        return employeeService.getByDepartment(departmentId);
    }

    // FIND EMAIL
    @GetMapping("/email")
    public EmployeeResponse getByEmail(
            @RequestParam String email) {
        return employeeService.findByEmail(email);
    }

    @PostMapping("/{id}/register-face")
    public EmployeeResponse registerFace(
            @PathVariable Integer id,
            @RequestPart MultipartFile image
    ) {
        return employeeService.registerFace(id, image);
    }

    @GetMapping("/{id}/has-face")
    public Boolean hasFace(
            @PathVariable Integer id
    ) {
        return employeeService.hasFace(id);
    }
}
package com.example.hrm.service.impl;

import com.example.hrm.entity.EmployeeInformation;
import com.example.hrm.repository.EmployeeInformationRepository;
import com.example.hrm.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeInformationRepository employeeRepo;

    @Override
    public List<EmployeeInformation> getAll() {
        return employeeRepo.findAll();
    }

    @Override
    public EmployeeInformation getById(Integer id) {
        return employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public EmployeeInformation create(EmployeeInformation employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public EmployeeInformation update(Integer id, EmployeeInformation employee) {
        EmployeeInformation old = getById(id);

        old.setFullName(employee.getFullName());
        old.setEmail(employee.getEmail());
        old.setPhone(employee.getPhone());
        old.setDepartment(employee.getDepartment());
        old.setPosition(employee.getPosition());

        return employeeRepo.save(old);
    }

    @Override
    public void delete(Integer id) {
        employeeRepo.deleteById(id);
    }

    @Override
    public List<EmployeeInformation> getByDepartment(Integer departmentId) {
        return employeeRepo.findByDepartment_Id(departmentId);
    }

    @Override
    public EmployeeInformation findByEmail(String email) {
        return employeeRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email not found"));
    }
}

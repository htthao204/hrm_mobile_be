package com.example.hrm.service.impl;

import com.example.hrm.dto.request.EmployeeCreateRequest;
import com.example.hrm.dto.response.EmployeeResponse;
import com.example.hrm.entity.*;
import com.example.hrm.mapper.EmployeeMapper;
import com.example.hrm.repository.*;
import com.example.hrm.service.EmployeeService;
import com.example.hrm.type.Gender;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeInformationRepository employeeRepo;
    private final DepartmentRepository departmentRepo;
    private final PositionRepository positionRepo;
    private final AccountRepository accountRepo;
    private final RoleRepository roleRepo;
    private final CountryRepository countryRepo;
    private final EmployeeMapper employeeMapper;
    private final PasswordEncoder passwordEncoder;

    // ================= MAP =================
    private EmployeeResponse map(EmployeeInformation emp) {
        return employeeMapper.toResponse(
                emp,
                emp.getPrivateInformation()
        );
    }

    // ================= CREATE =================
    @Override
    public EmployeeResponse create(EmployeeCreateRequest request) {

        // ===== ACCOUNT =====
        Role role = roleRepo.findByName("EMPLOYEE")
                .orElseThrow(() -> new RuntimeException("Role EMPLOYEE not found"));

        Account account = new Account();
        account.setUsername(request.getEmail());
        account.setPassword(passwordEncoder.encode("a123456"));
        account.setRole(role);
        account.setActive(true);
        account.setFirstLogin(true);

        accountRepo.save(account);

        // ===== EMPLOYEE =====
        EmployeeInformation employee =
                employeeMapper.toEmployee(request);

        employee.setAccount(account);

        if (request.getDepartmentId() != null) {
            employee.setDepartment(
                    departmentRepo.findById(request.getDepartmentId())
                            .orElseThrow(() -> new RuntimeException("Department not found"))
            );
        }

        employee.setPosition(
                positionRepo.findById(request.getPositionId())
                        .orElseThrow(() -> new RuntimeException("Position not found"))
        );

        // ===== PRIVATE INFO =====
        EmployeePrivateInformation info =
                employeeMapper.toPrivateInformation(request);

        info.setEmployee(employee);
        employee.setPrivateInformation(info);

        if (request.getGender() != null) {
            info.setGender(Gender.valueOf(request.getGender()));
        }

        if (request.getCountry() != null
                && request.getCountry().getId() != null) {

            info.setCountry(
                    countryRepo.findById(request.getCountry().getId())
                            .orElseThrow(() -> new RuntimeException("Country not found"))
            );
        }

        // ⭐ SAVE 1 LẦN DUY NHẤT
        employeeRepo.save(employee);

        return map(employee);
    }

    // ================= GET ALL =================
    @Override
    public List<EmployeeResponse> getAll() {
        return employeeRepo.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    // ================= GET BY ID =================
    @Override
    public EmployeeResponse getById(Integer id) {
        return map(
                employeeRepo.findById(id)
                        .orElseThrow(() -> new RuntimeException("Employee not found"))
        );
    }

    // ================= UPDATE =================
    @Override
    public EmployeeResponse update(Integer id, EmployeeCreateRequest request) {

        EmployeeInformation emp =
                employeeRepo.findById(id)
                        .orElseThrow(() -> new RuntimeException("Employee not found"));

        // ===== BASIC INFO =====
        employeeMapper.updateEmployee(emp, request);

        if (request.getDepartmentId() != null) {
            emp.setDepartment(
                    departmentRepo.findById(request.getDepartmentId())
                            .orElseThrow(() -> new RuntimeException("Department not found"))
            );
        }

        emp.setPosition(
                positionRepo.findById(request.getPositionId())
                        .orElseThrow(() -> new RuntimeException("Position not found"))
        );

        // ===== PRIVATE INFO =====
        EmployeePrivateInformation info = emp.getPrivateInformation();

        if (info == null) {
            info = new EmployeePrivateInformation();
            info.setEmployee(emp);
            emp.setPrivateInformation(info);
        }

        employeeMapper.updatePrivateInformation(info, request);

        if (request.getGender() != null) {
            info.setGender(Gender.valueOf(request.getGender()));
        }

        if (request.getCountry() != null
                && request.getCountry().getId() != null) {

            info.setCountry(
                    countryRepo.findById(request.getCountry().getId())
                            .orElseThrow(() -> new RuntimeException("Country not found"))
            );
        }

        // ⭐ SAVE ROOT ONLY
        employeeRepo.save(emp);

        return map(emp);
    }

    // ================= DELETE =================
    @Override
    public void delete(Integer id) {
        employeeRepo.deleteById(id);
    }

    // ================= FILTER =================
    @Override
    public List<EmployeeResponse> getByDepartment(Integer departmentId) {
        return employeeRepo.findByDepartment_Id(departmentId)
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public EmployeeResponse findByEmail(String email) {
        return map(
                employeeRepo.findByEmail(email)
                        .orElseThrow(() -> new RuntimeException("Email not found"))
        );
    }
}
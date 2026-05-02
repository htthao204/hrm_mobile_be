package com.example.hrm.mapper;

import com.example.hrm.dto.request.EmployeeCreateRequest;
import com.example.hrm.dto.response.EmployeeResponse;
import com.example.hrm.entity.EmployeeInformation;
import com.example.hrm.entity.EmployeePrivateInformation;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    // ================= EMPLOYEE =================

    public EmployeeInformation toEmployee(EmployeeCreateRequest request) {

        EmployeeInformation emp = new EmployeeInformation();

        emp.setFullName(request.getFullName());
        emp.setEmail(request.getEmail());
        emp.setPhone(request.getPhone());
        emp.setHireDate(request.getHireDate());

        return emp;
    }

    public void updateEmployee(
            EmployeeInformation emp,
            EmployeeCreateRequest request
    ) {
        emp.setFullName(request.getFullName());
        emp.setEmail(request.getEmail());
        emp.setPhone(request.getPhone());
        emp.setHireDate(request.getHireDate());
    }

    // ================= PRIVATE INFO =================

    public EmployeePrivateInformation toPrivateInformation(
            EmployeeCreateRequest request
    ) {
        EmployeePrivateInformation info =
                new EmployeePrivateInformation();

        updatePrivateInformation(info, request);

        return info;
    }

    public void updatePrivateInformation(
            EmployeePrivateInformation info,
            EmployeeCreateRequest request
    ) {
        info.setDateOfBirth(request.getDateOfBirth());
        info.setNationalId(request.getNationalId());
        info.setAddress(request.getAddress());
    }

    // ================= RESPONSE =================

    public EmployeeResponse toResponse(
            EmployeeInformation emp,
            EmployeePrivateInformation info
    ) {

        return EmployeeResponse.builder()
                .id(emp.getId())
                .fullName(emp.getFullName())
                .email(emp.getEmail())
                .phone(emp.getPhone())
                .hireDate(emp.getHireDate())

                .departmentName(
                        emp.getDepartment() != null
                                ? emp.getDepartment().getName()
                                : null
                )

                .positionName(
                        emp.getPosition() != null
                                ? emp.getPosition().getName()
                                : null
                )

                // ===== ACCOUNT MAPPING =====
                .accountId(
                        emp.getAccount() != null
                                ? emp.getAccount().getId()
                                : null
                )
                .username(
                        emp.getAccount() != null
                                ? emp.getAccount().getUsername()
                                : null
                )

                // ===== PRIVATE INFO =====
                .dateOfBirth(
                        info != null ? info.getDateOfBirth() : null
                )

                .gender(
                        info != null && info.getGender() != null
                                ? info.getGender().name()
                                : null
                )

                .nationalId(
                        info != null ? info.getNationalId() : null
                )

                .country(
                        info != null && info.getCountry() != null
                                ? info.getCountry().getName()
                                : null
                )

                .address(
                        info != null ? info.getAddress() : null
                )
                .build();
    }
}
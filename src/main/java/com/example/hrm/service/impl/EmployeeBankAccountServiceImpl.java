package com.example.hrm.service.impl;

import com.example.hrm.entity.EmployeeBankAccount;
import com.example.hrm.repository.EmployeeBankAccountRepository;
import com.example.hrm.service.EmployeeBankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeBankAccountServiceImpl implements EmployeeBankAccountService {

    private final EmployeeBankAccountRepository bankAccountRepo;

    @Override
    public List<EmployeeBankAccount> getAll() {
        return bankAccountRepo.findAll();
    }

    @Override
    public List<EmployeeBankAccount> getByEmployeeId(Integer employeeId) {
        return bankAccountRepo.findByEmployee_Id(employeeId);
    }

    @Override
    public EmployeeBankAccount getById(Integer id) {
        return bankAccountRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản ngân hàng với ID: " + id));
    }

    @Override
    @Transactional
    public EmployeeBankAccount create(EmployeeBankAccount bankAccount) {
        // Bạn có thể thêm logic kiểm tra xem tài khoản này đã tồn tại chưa (số tài khoản trùng)
        return bankAccountRepo.save(bankAccount);
    }

    @Override
    @Transactional
    public EmployeeBankAccount update(Integer id, EmployeeBankAccount details) {
        EmployeeBankAccount account = getById(id);

        account.setBankName(details.getBankName());
        account.setAccountNumber(details.getAccountNumber());
        return bankAccountRepo.save(account);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        EmployeeBankAccount account = getById(id);
        bankAccountRepo.delete(account);
    }
}
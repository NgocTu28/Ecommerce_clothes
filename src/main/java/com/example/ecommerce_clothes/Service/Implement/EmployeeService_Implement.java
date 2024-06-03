package com.example.ecommerce_clothes.Service.Implement;

import com.example.ecommerce_clothes.Model.Employee;
import com.example.ecommerce_clothes.Repository.Employee_Repository;
import com.example.ecommerce_clothes.Service.Employee_Service;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class EmployeeService_Implement implements Employee_Service {
    private final Employee_Repository employeeRepository;
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee save(Employee employee) throws ChangeSetPersister.NotFoundException {
        Employee employeeFind = employeeRepository.findByCode(employee.getCode_Employee());
        try {
            if(employeeFind == null){
                employeeRepository.save(employee);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Integer id, Employee employee) throws ChangeSetPersister.NotFoundException {
        Employee employeeFind = employeeRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        if(employeeFind != null){
            employeeFind.setCode_Employee(employee.getCode_Employee());
            employeeFind.setName_Employee(employee.getName_Employee());
            employeeFind.setStatic_Employee(employee.getStatic_Employee());
            employeeRepository.save(employeeFind);
        }
    }

    @Override
    public void removeOrRever(Integer id) throws ChangeSetPersister.NotFoundException {
        Employee employeeFind = employeeRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        if(employeeFind != null){
            employeeFind.setStatic_Employee(0);
            employeeRepository.save(employeeFind);
        }
    }
}

package com.example.ecommerce_clothes.Repository;

import com.example.ecommerce_clothes.Model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface Employee_Repository extends JpaRepository<Employee, Integer> {
    @Query(value = "SELECT e FROM Employee e WHERE e.code_Employee = :code_Employee")
    Employee findByCode(@Param("code_Employee") String code);
}

package com.example.ecommerce_clothes.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Name_Employee")
    private String name_Employee;
    @Column(name = "code_Employee")
    private String code_Employee;
    @Column(name = "UserName_Employee")
    private String userName_Employee;
    @Column(name = "Password_Employee")
    private String password_Employee;
    @Column(name = "Role_Employee")
    private String role_Employee;
    @Column(name = "Static")
    private Integer static_Employee;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bill> billList;
}

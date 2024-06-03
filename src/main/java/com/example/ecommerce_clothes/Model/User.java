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
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Name_User")
    private String name_User;
    @Column(name = "PhoneNumber_User")
    private String phoneNumber_User;
    @Column(name = "Code_User")
    private String code_User;
    @Column(name = "Status")
    private Integer status;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bill> billList;
}

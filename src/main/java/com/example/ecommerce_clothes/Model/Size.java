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
@Table(name = "Size")
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Code_Size")
    private String code_Size;
    @Column(name = "name_Size")
    private String name_Size;
    @Column(name = "Status")
    private int status;

    @OneToMany(mappedBy = "size", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductDetail> productDetailList;
}

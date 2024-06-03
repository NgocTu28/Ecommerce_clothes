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
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Code_Product")
    private String code_Product;
    @Column(name = "name_Product")
    private String name_Product;
    @Column(name = "Status")
    private int status_Product;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductDetail> productDetailList;

}

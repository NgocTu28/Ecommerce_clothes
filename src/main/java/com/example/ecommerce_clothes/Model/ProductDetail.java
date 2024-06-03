package com.example.ecommerce_clothes.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ProductDetail")

public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Code_ProductDetail")
    private String code_ProductDetail;
    @Column(name = "quanlity_ProductDetail")
    private int quanlity_ProductDetail;
    @Column(name = "UnitPrice_ProductDetail")
    private BigDecimal unitPrice_ProductDetail;
    @Column(name = "Stauts")
    private int status_ProductDetail;
    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "size")
    private Size size;
    @ManyToOne
    @JoinColumn(name = "color")
    private Color color;
    @OneToMany(mappedBy = "productDetail", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BillDetail> billDetail;

}

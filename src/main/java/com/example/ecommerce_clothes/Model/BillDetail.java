package com.example.ecommerce_clothes.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BillDetail")
public class BillDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Quanlity_BillDetail")
    private int quanlity_BillDetail;
    @Column(name = "TotalAmount_BillDetail")
    private BigDecimal totalAmount_BillDetail;
    @Column(name = "Static")
    private int Static;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "productDetail_id")
    private  ProductDetail productDetail;
}

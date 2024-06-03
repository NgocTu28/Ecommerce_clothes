package com.example.ecommerce_clothes.Repository;

import com.example.ecommerce_clothes.Model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Product_Repository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT p FROM Product p WHERE p.name_Product like %:keyWord% OR p.code_Product like %:keyWord%")
    Page<Product> findByKeyWord(String keyWord, Pageable pageable);

    Page<Product> findAll(Pageable pageable);

    @Query(value = "SELECT p FROM Product p WHERE p.code_Product like :code")
    Product findByCode(@Param("code") String code);

}

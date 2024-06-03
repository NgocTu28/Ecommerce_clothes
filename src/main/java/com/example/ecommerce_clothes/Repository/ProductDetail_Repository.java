package com.example.ecommerce_clothes.Repository;

import com.example.ecommerce_clothes.Model.Product;
import com.example.ecommerce_clothes.Model.ProductDetail;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductDetail_Repository extends JpaRepository<ProductDetail, Integer> {
    @Query(value = "SELECT pd FROM ProductDetail pd WHERE pd.product.id = :idProduct")
    List<ProductDetail> findProductDetailByIdProduct(@Param("idProduct") Integer idProduct);

    @Query(value = "SELECT pd FROM ProductDetail pd WHERE pd.code_ProductDetail like %:keyWord%")
    List<Product> findByKeyWord(@Param("keyWord") String keyWord, Pageable pageable);

    @Query(value = "SELECT pd FROM ProductDetail pd WHERE pd.code_ProductDetail like :code")
    ProductDetail findByCode(@Param("code") String code);

    @Modifying
    @Transactional
    @Query("UPDATE ProductDetail pd SET pd.status_ProductDetail = 3 WHERE pd.quanlity_ProductDetail = 0")
    void updateProductDetail();
}

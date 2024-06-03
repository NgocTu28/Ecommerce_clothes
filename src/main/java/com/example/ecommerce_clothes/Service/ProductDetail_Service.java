package com.example.ecommerce_clothes.Service;

import com.example.ecommerce_clothes.Model.Product;
import com.example.ecommerce_clothes.Model.ProductDetail;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductDetail_Service {
    List<ProductDetail> findAll();
    Page<ProductDetail> findAll(Pageable pageable);
    Optional<ProductDetail> findById(Integer id);
    ProductDetail save(ProductDetail productDetail) throws ChangeSetPersister.NotFoundException;
    void update(Integer id, ProductDetail productDetail) throws ChangeSetPersister.NotFoundException;
    List<Product> search(String keyword, Pageable pageable);
    void removeOrRever(Integer id) throws ChangeSetPersister.NotFoundException;
    List<ProductDetail> findProductDetailByIdProduct(Integer idProduct);
}

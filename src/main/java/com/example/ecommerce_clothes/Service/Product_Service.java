package com.example.ecommerce_clothes.Service;

import com.example.ecommerce_clothes.Model.Product;
import com.example.ecommerce_clothes.Model.Size;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface Product_Service {
    List<Product> findAll();
    Page<Product> findAll(Pageable pageable);
    Optional<Product> findById(Integer id);
    Product save(Product product) throws ChangeSetPersister.NotFoundException;
    void update(Integer id, Product product) throws ChangeSetPersister.NotFoundException;
    Page<Product> search(String keyword, Pageable pageable);
    void removeOrRever(Integer id) throws ChangeSetPersister.NotFoundException;
}

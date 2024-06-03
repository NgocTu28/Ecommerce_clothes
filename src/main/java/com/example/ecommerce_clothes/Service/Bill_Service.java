package com.example.ecommerce_clothes.Service;

import com.example.ecommerce_clothes.Model.Bill;
import com.example.ecommerce_clothes.Model.Product;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface Bill_Service {
    List<Bill> findAll();
    Optional<Bill> findById(Integer id);
    Bill save(Bill bill) throws ChangeSetPersister.NotFoundException;
    void update(Integer id, Bill bill) throws ChangeSetPersister.NotFoundException;
    Page<Bill> searchByUser(String keyword, Pageable pageable);
    void removeOrRever(Integer id) throws ChangeSetPersister.NotFoundException;
}

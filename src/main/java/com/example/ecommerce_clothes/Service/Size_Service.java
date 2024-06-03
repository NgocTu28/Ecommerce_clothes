package com.example.ecommerce_clothes.Service;

import com.example.ecommerce_clothes.Model.Size;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface Size_Service {
    List<Size> findAll();
    Page<Size> findAll(Pageable pageable);
    Optional<Size> findById(Integer id);
    Size save(Size size) throws ChangeSetPersister.NotFoundException;
    void update(Integer id, Size size) throws ChangeSetPersister.NotFoundException;
    Page<Size> search(String keyword, Pageable pageable);
    void removeOrRever(Integer id) throws ChangeSetPersister.NotFoundException;
}

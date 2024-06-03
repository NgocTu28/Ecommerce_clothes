package com.example.ecommerce_clothes.Service;

import com.example.ecommerce_clothes.Model.User;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface User_Service {
    List<User> findAll();
    Page<User> findAll(Pageable pageable);
    Optional<User> findById(Integer id);
    User save(User user) throws ChangeSetPersister.NotFoundException;
    void update(Integer id, User user) throws ChangeSetPersister.NotFoundException;
    Page<User> search(String keyword, Pageable pageable);
    void removeOrRever(Integer id) throws ChangeSetPersister.NotFoundException;
}

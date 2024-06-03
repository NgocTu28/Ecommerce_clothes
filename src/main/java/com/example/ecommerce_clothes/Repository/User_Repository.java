package com.example.ecommerce_clothes.Repository;

import com.example.ecommerce_clothes.Model.Size;
import com.example.ecommerce_clothes.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface User_Repository extends JpaRepository<User, Integer> {
    @Override
    Page<User> findAll(Pageable pageable);
    @Query(value = "SELECT s FROM User s WHERE s.name_User like %:keyWord% OR s.code_User like %:keyWord%")
    Page<User> findByKeyWord(String keyWord, Pageable pageable);
}

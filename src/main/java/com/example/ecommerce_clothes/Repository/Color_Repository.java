package com.example.ecommerce_clothes.Repository;

import com.example.ecommerce_clothes.Model.Color;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Color_Repository extends JpaRepository<Color, Integer> {
    @Query(value = "SELECT c FROM Color c WHERE c.name_Color like %:keyWord% OR c.code_Color like %:keyWord%")
    Page<Color> findByKeyWord(String keyWord, Pageable pageable);
}

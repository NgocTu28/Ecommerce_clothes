package com.example.ecommerce_clothes.Repository;

import com.example.ecommerce_clothes.Model.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Size_Repository extends JpaRepository<Size, Integer> {
    @Override
    Page<Size> findAll(Pageable pageable);
    @Query(value = "SELECT s FROM Size s WHERE s.name_Size like %:keyWord% OR s.code_Size like %:keyWord%")
    Page<Size> findByKeyWord(String keyWord, Pageable pageable);
    @Query(value = "UPDATE Size s SET s.status = :status WHERE s.id = :id")
    void updateOrRevert(@Param("id") Integer id,@Param("status") Integer status);
}

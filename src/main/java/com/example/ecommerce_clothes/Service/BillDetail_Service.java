package com.example.ecommerce_clothes.Service;

import com.example.ecommerce_clothes.Model.BillDetail;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface BillDetail_Service {
    List<BillDetail> findAll();

    List<BillDetail> findAllByBillId(Integer billId);

    BillDetail findById(Integer id) throws ChangeSetPersister.NotFoundException;

    BillDetail save(BillDetail billDetailDto) throws ChangeSetPersister.NotFoundException;

    void update(Integer id, BillDetail billDetailDto) throws ChangeSetPersister.NotFoundException;

    void removeOrRever(Integer id) throws ChangeSetPersister.NotFoundException;
}

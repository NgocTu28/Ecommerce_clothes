package com.example.ecommerce_clothes.Service.Implement;

import com.example.ecommerce_clothes.Model.Bill;
import com.example.ecommerce_clothes.Model.Product;
import com.example.ecommerce_clothes.Repository.Bill_Repository;
import com.example.ecommerce_clothes.Service.Bill_Service;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BillService_Implement implements Bill_Service {
    private final Bill_Repository billRepository;

    @Override
    public List<Bill> findAll() {
        return billRepository.findAll();
    }

    @Override
    public List<Bill> findAllBillActive() {
        return billRepository.getBillActive();
    }

    @Override
    public Optional<Bill> findById(Integer id) {
        return billRepository.findById(id);
    }

    @Override
    public Bill save(Bill bill) throws ChangeSetPersister.NotFoundException {
        return billRepository.save(bill);
    }

    @Override
    public void update(Integer id, Bill bill) throws ChangeSetPersister.NotFoundException {
        Bill billFind = billRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        if (billFind != null && billFind.getStatus() != 0) {
            billFind.setCode_Bill(bill.getCode_Bill());
            billFind.setUser(bill.getUser());
            billFind.setEmployee(bill.getEmployee());
            billFind.setStatus(bill.getStatus());
            billFind.setDateOfPurchase(bill.getDateOfPurchase());
            billRepository.save(billFind);
        }
    }

    @Override
    public Page<Bill> searchByUser(String keyword, Pageable pageable) {
        return (Page<Bill>) billRepository.findBillByUser(keyword);
    }

    @Override
    public void removeOrRever(Integer id) throws ChangeSetPersister.NotFoundException {
        Bill billFind = billRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        if (billFind != null) {
            billFind.setStatus(0);
            billRepository.save(billFind);
        }
    }
}

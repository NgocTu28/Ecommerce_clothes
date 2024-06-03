package com.example.ecommerce_clothes.Service.Implement;

import com.example.ecommerce_clothes.Model.BillDetail;
import com.example.ecommerce_clothes.Model.ProductDetail;
import com.example.ecommerce_clothes.Repository.BillDetail_Repository;
import com.example.ecommerce_clothes.Repository.ProductDetail_Repository;
import com.example.ecommerce_clothes.Service.BillDetail_Service;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class BillDetailService_Implement implements BillDetail_Service {
    private final BillDetail_Repository billDetailRepository;
    private final ProductDetail_Repository productDetailRepository;

    @Override
    public List<BillDetail> findAll() {
        return billDetailRepository.findAll();
    }

    @Override
    public List<BillDetail> findAllByBillId(Integer billId) {
        return billDetailRepository.findAllByBillId(billId);
    }

    @Override
    public BillDetail findById(Integer id) throws ChangeSetPersister.NotFoundException {
        return billDetailRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }

    @Override
    public BillDetail save(BillDetail billDetail) throws ChangeSetPersister.NotFoundException {
        ProductDetail productDetail = productDetailRepository.findById(billDetail.getProductDetail().getId()).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        BigDecimal total = productDetail.getUnitPrice_ProductDetail().multiply(BigDecimal.valueOf(billDetail.getQuanlity_BillDetail()));
        billDetail.setTotalAmount_BillDetail(total);
        return billDetailRepository.save(billDetail);
    }

    @Override
    public void update(Integer id, BillDetail billDetailDto) throws ChangeSetPersister.NotFoundException {
        BillDetail billDetailFind = billDetailRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        if(billDetailFind != null && billDetailFind.getStatic() != 0){
            if(billDetailDto.getQuanlity_BillDetail() > 0){
                billDetailFind.setProductDetail(billDetailDto.getProductDetail());
                if(billDetailDto.getQuanlity_BillDetail() > 0){
                    billDetailFind.setQuanlity_BillDetail(billDetailDto.getQuanlity_BillDetail());
                }
                billDetailFind.setStatic(billDetailDto.getStatic());
                ProductDetail productDetail = productDetailRepository.findById(billDetailDto.getProductDetail().getId()).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
                BigDecimal total = productDetail.getUnitPrice_ProductDetail().multiply(BigDecimal.valueOf(billDetailDto.getQuanlity_BillDetail()));
                billDetailDto.setTotalAmount_BillDetail(total);
            }
        }
    }

    @Override
    public void removeOrRever(Integer id) throws ChangeSetPersister.NotFoundException {
        BillDetail billDetailFind = billDetailRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        if(billDetailFind != null){
            billDetailFind.setStatic(0);
        }
    }


}

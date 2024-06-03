package com.example.ecommerce_clothes.Service.Implement;

import com.example.ecommerce_clothes.Model.Product;
import com.example.ecommerce_clothes.Model.ProductDetail;
import com.example.ecommerce_clothes.Repository.ProductDetail_Repository;
import com.example.ecommerce_clothes.Service.ProductDetail_Service;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductDetailService_Implement implements ProductDetail_Service {
    private final ProductDetail_Repository productDetailRepository;

    @Override
    public List<ProductDetail> findAll() {
        return productDetailRepository.findAll();
    }

    @Override
    public Page<ProductDetail> findAll(Pageable pageable) {
        return productDetailRepository.findAll(pageable);
    }

    @Override
    public Optional<ProductDetail> findById(Integer id) {
        return productDetailRepository.findById(id);
    }

    @Override
    public ProductDetail save(ProductDetail productDetail) throws ChangeSetPersister.NotFoundException {
        ProductDetail productDetailFind = productDetailRepository.findByCode(productDetail.getCode_ProductDetail());
        if(productDetailFind == null){
            if (productDetail.getQuanlity_ProductDetail() <= 0) {
                productDetailRepository.updateProductDetail();
            }
            productDetailRepository.save(productDetail);
        }
        return null;
    }

    @Override
    public void update(Integer id, ProductDetail productDetail) throws ChangeSetPersister.NotFoundException {
        ProductDetail productDetailFind = productDetailRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        if (productDetailFind != null) {
            productDetailFind.setProduct(productDetail.getProduct());
            productDetailFind.setCode_ProductDetail(productDetail.getCode_ProductDetail());
            productDetailFind.setQuanlity_ProductDetail(productDetail.getQuanlity_ProductDetail());
            productDetailFind.setColor(productDetail.getColor());
            productDetailFind.setSize(productDetail.getSize());
            productDetailFind.setUnitPrice_ProductDetail(productDetail.getUnitPrice_ProductDetail());
            if (productDetail.getQuanlity_ProductDetail() <= 0) {
                productDetailRepository.updateProductDetail();
            } else {
                productDetailFind.setStatus_ProductDetail(productDetail.getStatus_ProductDetail());
            }
            productDetailRepository.save(productDetailFind);
        }
    }

    @Override
    public List<Product> search(String keyword, Pageable pageable) {
        return productDetailRepository.findByKeyWord(keyword, pageable);
    }

    @Override
    public void removeOrRever(Integer id) throws ChangeSetPersister.NotFoundException {
        ProductDetail productDetailFind = productDetailRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        if (productDetailFind != null) {
            productDetailFind.setStatus_ProductDetail(0);
            productDetailRepository.save(productDetailFind);
        }
    }

    @Override
    public List<ProductDetail> findProductDetailByIdProduct(Integer idProduct) {
        return productDetailRepository.findProductDetailByIdProduct(idProduct);
    }
}

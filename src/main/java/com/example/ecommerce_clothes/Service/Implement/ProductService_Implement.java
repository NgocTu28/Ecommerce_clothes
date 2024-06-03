package com.example.ecommerce_clothes.Service.Implement;

import com.example.ecommerce_clothes.Model.Product;
import com.example.ecommerce_clothes.Repository.Product_Repository;
import com.example.ecommerce_clothes.Service.Product_Service;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class ProductService_Implement implements Product_Service {
    private final Product_Repository productRepository;
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) throws ChangeSetPersister.NotFoundException {
        Product productFind = productRepository.findByCode(product.getCode_Product());
        if(productFind == null){
            return productRepository.save(product);
        }
       return null;
    }

    @Override
    public void update(Integer id, Product product) throws ChangeSetPersister.NotFoundException {
        Product productFind = productRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        if(productFind != null){
            productFind.setCode_Product(product.getCode_Product());
            productFind.setName_Product(product.getName_Product());
            productFind.setStatus_Product(product.getStatus_Product());
            productRepository.save(productFind);
        }
    }

    @Override
    public Page<Product> search(String keyword, Pageable pageable) {
        return productRepository.findByKeyWord(keyword, pageable);
    }

    @Override
    public void removeOrRever(Integer id) throws ChangeSetPersister.NotFoundException {
        Product productFind = productRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        if(productFind != null){
            productFind.setStatus_Product(0);
            productRepository.save(productFind);
        }
    }
}

package com.example.ecommerce_clothes.Service.Implement;

import com.example.ecommerce_clothes.Model.Size;
import com.example.ecommerce_clothes.Repository.Size_Repository;
import com.example.ecommerce_clothes.Service.Size_Service;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SizeService_Implement implements Size_Service {
    private final Size_Repository sizeRepository;

    @Override
    public List<Size> findAll() {
        return sizeRepository.findAll();
    }

    @Override
    public Page<Size> findAll(Pageable pageable) {
        return sizeRepository.findAll(pageable);
    }

    @Override
    public Optional<Size> findById(Integer id) {
        return sizeRepository.findById(id);
    }

    @Override
    public Size save(Size size) throws ChangeSetPersister.NotFoundException {
            return sizeRepository.save(size);
    }

    @Override
    public void update(Integer id, Size size) throws ChangeSetPersister.NotFoundException {
        Size find = sizeRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        if (find != null) {
            find.setName_Size(size.getName_Size());
            find.setCode_Size(size.getCode_Size());
            find.setStatus(size.getStatus());
            sizeRepository.save(find);
        }
    }

    @Override
    public void removeOrRever(Integer id) throws ChangeSetPersister.NotFoundException {
        Size find = sizeRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        if (find != null) {
            find.setStatus(0);
            sizeRepository.save(find);
        }
    }

    @Override
    public Page<Size> search(String keyword, Pageable pageable) {
        return sizeRepository.findByKeyWord(keyword, pageable);
    }
}

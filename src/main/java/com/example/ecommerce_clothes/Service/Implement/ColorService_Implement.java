package com.example.ecommerce_clothes.Service.Implement;

import com.example.ecommerce_clothes.Model.Color;
import com.example.ecommerce_clothes.Repository.Color_Repository;
import com.example.ecommerce_clothes.Service.Color_Service;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ColorService_Implement implements Color_Service {
    private final Color_Repository colorRepository;

    @Override
    public List<Color> findAll() {
        return colorRepository.findAll();
    }

    @Override
    public Page<Color> findAll(Pageable pageable) {
        return colorRepository.findAll(pageable);
    }

    @Override
    public Optional<Color> findById(Integer id) {
        return colorRepository.findById(id);
    }

    @Override
    public Color save(Color color) throws ChangeSetPersister.NotFoundException {
        color.setStatus(1);
        colorRepository.save(color);
        return color;
    }

    @Override
    public void update(Integer id, Color color) throws ChangeSetPersister.NotFoundException {
        Color colorFind = colorRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        if (colorFind != null) {
            colorFind.setCode_Color(color.getCode_Color());
            colorFind.setName_Color(color.getName_Color());
            colorFind.setStatus(color.getStatus());
            colorRepository.save(colorFind);
        }
    }

    @Override
    public void removeOrRever(Integer id) throws ChangeSetPersister.NotFoundException {
        Color find = colorRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        if (find != null) {
            find.setStatus(0);
            colorRepository.save(find);
        }
    }

    @Override
    public Page<Color> search(String keyword, Pageable pageable) {
        return colorRepository.findByKeyWord(keyword, pageable);
    }
}

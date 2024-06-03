package com.example.ecommerce_clothes.Service.Implement;

import com.example.ecommerce_clothes.Model.User;
import com.example.ecommerce_clothes.Repository.User_Repository;
import com.example.ecommerce_clothes.Service.User_Service;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService_Implement implements User_Service {
    private final User_Repository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) throws ChangeSetPersister.NotFoundException {
        return userRepository.save(user);
    }

    @Override
    public void update(Integer id, User user) throws ChangeSetPersister.NotFoundException {
        User userFind = userRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        if (userFind != null) {
            userFind.setCode_User(user.getCode_User());
            userFind.setName_User(user.getName_User());
            userFind.setStatus(user.getStatus());
            userRepository.save(userFind);
        } else {
            System.out.println("========= Not Found User with ID: " + id);
        }
    }

    @Override
    public Page<User> search(String keyword, Pageable pageable) {
        List<User> findKeyWord = (List<User>) userRepository.findByKeyWord(keyword,pageable);
        return (Page<User>) findKeyWord;
    }

    @Override
    public void removeOrRever(Integer id) throws ChangeSetPersister.NotFoundException {
        User userFind = userRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        if (userFind != null) {
            userFind.setStatus(0);
        }
    }
}

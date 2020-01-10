package com.servicio.backend.service;

import com.servicio.backend.entity.User;
import com.servicio.backend.exception.RecordNotFoundException;
import com.servicio.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public Optional<User> getUserById(long id){
        return userRepo.findById(id);
    }

    public User addUser(User newUser){
        newUser.setCreate(new Date());
        return userRepo.save(newUser);
    }

    public User updateUserById(Long id_user, User user){
        User resp = userRepo.findById(id_user).get();
        resp.setId_user(id_user);
        if (user.getEmail() != null)
            resp.setEmail(user.getEmail());
        if (user.getLastName() != null)
            resp.setLastName(user.getLastName());
        if (user.getName() != null)
            resp.setName(user.getName());
        if (user.getPhone() != null)
            resp.setPhone(user.getPhone());
        if (user.getPassword() != null)
            resp.setPassword(user.getPassword());
        if (user.getAddress() != null)
            resp.setAddress(user.getAddress());
        if (user.getPicture() != null)
            resp.setPicture(user.getPicture());
        return userRepo.save(resp);
    }

    public boolean deleteUserById(long id) throws RecordNotFoundException {
        if (!userRepo.existsById(id))
            throw new RecordNotFoundException("Could not find user: " + id);
        userRepo.deleteById(id);
        return !userRepo.existsById(id);
    }

    public List<User> findUserByExample(User user){
        return userRepo.findAll(Example.of(user));
    }

    public boolean existUserById(long id){
        return userRepo.existsById(id);
    }

    public boolean existUserByExample(User exampleUser){
        return userRepo.exists(Example.of(exampleUser));
    }
}

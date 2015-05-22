package com.vladimir.testService.service.impl;

import com.vladimir.testService.entity.User;
import com.vladimir.testService.repository.UserRepository;
import com.vladimir.testService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    @Resource
    private UserRepository userRepository;

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) {
        User savedUser = userRepository.saveAndFlush(user);
        return savedUser;
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    public User getOne(Long id) {
        return userRepository.findOne(id);
    }

    /*@Override
    public User getByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User getBySurname(String surname) {
        return userRepository.findBySurname(surname);
    }*/

    @Override
    public User editUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}

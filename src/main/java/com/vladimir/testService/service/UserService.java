package com.vladimir.testService.service;

import com.vladimir.testService.entity.User;

import java.util.List;


public interface UserService {

    User addUser(User user);
    void delete(Long id);
    User getOne(Long id);
    /*User getByName(String name);
    User getBySurname(String surname);*/
    User editUser(User user);
    List<User> getAll();

}

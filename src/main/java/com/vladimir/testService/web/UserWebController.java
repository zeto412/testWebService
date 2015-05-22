package com.vladimir.testService.web;

import com.vladimir.testService.entity.User;
import com.vladimir.testService.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/service")
public class UserWebController {

    private static final String prefix = "/user";

    @Resource
    private UserService _userService;

    @RequestMapping(value = prefix + "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User getUser(@PathVariable long id) {
        return _userService.getOne(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public User addUser(User user) {
        return _userService.addUser(user);
    }

    @RequestMapping(value = prefix +  "/{id}", method = RequestMethod.DELETE)
    //@ResponseBody
    public void deleteUser(@PathVariable long id) {
        _userService.delete(id);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUsers() {
        return _userService.getAll();
    }

    public UserService getUserService() {
        return _userService;
    }

    public void setUserService(UserService userService) {
        _userService = userService;
    }
}

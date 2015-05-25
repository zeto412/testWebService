package com.vladimir.testService.service;

import com.vladimir.testService.config.TestDataBaseConfig;
import com.vladimir.testService.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataBaseConfig.class)
@WebAppConfiguration
public class UserServiceTest {

    @Resource
    UserService _userService;

    private int userCountBefore;

    @Before
    public void before() throws Exception {
        List<User> users = new LinkedList<>();

        users.add(new User("User", "One"));
        users.add(new User("User", "Two"));
        users.add(new User("User", "Three"));
        users.add(new User("User", "Four"));
        users.add(new User("User", "Five"));
        users.add(new User("User", "Six"));
        users.add(new User("User", "Seven"));

        for(User user : users) {
            _userService.addUser(user);
        }

        userCountBefore = _userService.getAll().size();

        System.out.println(userCountBefore + " users added");
    }

    @After
    public void after() throws Exception {
        for(User user : _userService.getAll()) {
            _userService.delete(user.getId());
        }
    }

    @Test
    public void testAddUser() throws Exception {

        int sizeOfUserListBefore = _userService.getAll().size();

        String name = "Test";
        String surname = "User";
        User user = new User(name, surname);
        user = _userService.addUser(user);

        int sizeOfUserListAfter = _userService.getAll().size();

        assertEquals(sizeOfUserListBefore + 1, sizeOfUserListAfter);

        User userToCheck = _userService.getOne(user.getId());
        assertEquals(userToCheck.getName(), user.getName());

    }

    @Test
    public void testGetOne() throws Exception {
        List<User> users = _userService.getAll();
        User user = _userService.getOne(users.get(2).getId());
        assertEquals("Three", user.getSurname());
    }

    @Test
    public void testDelete() throws Exception {
        List<User> users = _userService.getAll();
        int sizeOfUserListBefore = users.size();
        _userService.delete(users.get(1).getId());
        int sizeOfUserListAfter = _userService.getAll().size();

        assertEquals(sizeOfUserListBefore - 1, sizeOfUserListAfter);
    }

    @Test
    public void testEditUser() throws Exception {
        User user = _userService.getOne(4L);

        String oldName = user.getName();
        String newName = "Edited";

        user.setName(newName);
        user = _userService.editUser(user);

        assertNotEquals(oldName, user.getName());
        assertEquals(newName, user.getName());

    }

    @Test
    public void getAll() throws Exception {
        int actualUserCount = _userService.getAll().size();
        assertEquals(userCountBefore, actualUserCount);
    }


}
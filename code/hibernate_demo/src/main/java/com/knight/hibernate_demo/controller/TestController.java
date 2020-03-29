package com.knight.hibernate_demo.controller;

import com.knight.hibernate_demo.dao.UserDao;
import com.knight.hibernate_demo.model.Event;
import com.knight.hibernate_demo.model.User1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {


    private UserDao userDao;

    @Autowired
    public TestController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/hi")
    public String hi() {
        Event theEvent = new Event();
        theEvent.setTitle("hello");
        User1 user1= User1.builder().userId(1).userName("KNight999").build();
        this.userDao.updateUser(user1);
        this.userDao.saveEvent(theEvent);

        return "hello world";
    }
}

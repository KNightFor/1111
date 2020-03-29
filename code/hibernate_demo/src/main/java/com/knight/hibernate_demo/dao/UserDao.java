package com.knight.hibernate_demo.dao;


import com.knight.hibernate_demo.model.Event;
import com.knight.hibernate_demo.model.User1;

public interface UserDao {
    User1 findByUserID(int id);

    void updateUser(User1 user);


    void saveEvent(Event event);
}

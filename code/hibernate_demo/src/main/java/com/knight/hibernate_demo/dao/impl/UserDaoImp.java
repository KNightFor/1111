package com.knight.hibernate_demo.dao.impl;

import com.knight.hibernate_demo.config.HibernateSessionFactory;
import com.knight.hibernate_demo.dao.UserDao;
import com.knight.hibernate_demo.model.Event;
import com.knight.hibernate_demo.model.User1;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

@Service
public class UserDaoImp implements UserDao {

    public User1 findByUserID(int id) {
        Session session = HibernateSessionFactory.getSession();//获得Session对象
        User1 user = (User1) session.get(User1.class, id);
        HibernateSessionFactory.closeSession();//关闭Session对象
        return user;
    }

    public void updateUser(User1 user) {
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        }
        HibernateSessionFactory.closeSession();
    }

    public void saveEvent(Event event) {
        Session session = HibernateSessionFactory.getSession();//获得Session对象
        Transaction transaction = null;//声明一个事务对象
        try {
            transaction = session.beginTransaction();//开启事务
            session.save(event);//更新学生信息
            transaction.commit();//提交事务
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();//事务回滚
        }
        HibernateSessionFactory.closeSession();//关闭Session对象
    }
}

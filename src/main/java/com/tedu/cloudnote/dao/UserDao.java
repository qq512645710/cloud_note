package com.tedu.cloudnote.dao;
import java.util.List;

import com.tedu.cloudnote.entity.User;

public interface UserDao {
     public User findByName(String name);
     public void save(User user);
     public List<User> findAll();
     public User findById(String id);
     public int updateToken(User user);
}

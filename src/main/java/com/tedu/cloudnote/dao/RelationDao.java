package com.tedu.cloudnote.dao;

import java.util.List;

import com.tedu.cloudnote.entity.Book;
import com.tedu.cloudnote.entity.User;

public interface RelationDao {
     public User findUserAndBooks(String userId);
     public User findUserBooks(String userId);
     public List<Book> findBookAndUser();
     public List<Book> findBookUser();
}

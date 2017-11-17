package com.tedu.cloudnote.dao;

import java.util.List;

import com.tedu.cloudnote.entity.Book;

public interface BookDao {
    public List<Book> findByUserId(String userId);
    public List<Book> findAll();
    public void addBook(Book book);
}

package com.tedu.cloudnote.service;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tedu.cloudnote.dao.BookDao;
import com.tedu.cloudnote.dao.UserDao;
import com.tedu.cloudnote.entity.Book;
import com.tedu.cloudnote.util.NoteResult;
import com.tedu.cloudnote.util.NoteUtil;
@Service("bookService")//扫描到spring容器
@Transactional
public class BookServiceImpl implements BookService {
	@Resource//注入dao对象
	private BookDao dao;
	public NoteResult<List<Book>> loadUserBooks(String userId) {
		NoteResult<List<Book>> result=new NoteResult<List<Book>>();
		List<Book> list=dao.findByUserId(userId);
		//返回结果
		result.setStatus(0);
		result.setMsg("查询笔记本完成");
		result.setData(list);
		return result;
	}
	public NoteResult<Book> addBook(String name, String userId) {
		NoteResult<Book> result=new NoteResult<Book>();
		Book book=new Book();
		book.setCn_user_id(userId);
		book.setCn_notebook_name(name);
		Timestamp timestamp=new Timestamp(System.currentTimeMillis());
		book.setCn_notebook_createtime(timestamp);
		book.setCn_notebook_id(NoteUtil.createId());
		book.setCn_notebook_type_id("5");
		dao.addBook(book);
			result.setStatus(0);
			result.setMsg("增加笔记本成功！");
			result.setData(book);
		return result;
	}

}

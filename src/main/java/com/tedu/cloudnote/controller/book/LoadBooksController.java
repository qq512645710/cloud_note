package com.tedu.cloudnote.controller.book;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnote.entity.Book;
import com.tedu.cloudnote.service.BookService;
import com.tedu.cloudnote.util.NoteResult;

@Controller
@RequestMapping("/book")
public class LoadBooksController {
	   @Resource
       private BookService bookService;
	   @RequestMapping("/loadbooks")
	   @ResponseBody
	   public NoteResult<List<Book>> execute(String userId){
		   NoteResult<List<Book>> result=bookService.loadUserBooks(userId);
		   return result;
		   
	   }
}

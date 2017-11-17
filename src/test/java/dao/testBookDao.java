package dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sun.org.apache.xml.internal.security.Init;
import com.tedu.cloudnote.dao.BookDao;
import com.tedu.cloudnote.entity.Book;

import test.TestBase;

public class testBookDao extends TestBase{
	   private BookDao dao;
       @Before
       public void Init(){
    	 dao=super.getConText().getBean("bookDao",BookDao.class);
       }
       @Test
       public void test(){
    	   List<Book> books=dao.findByUserId("48595f52-b22c-4485-9244-f4004255b972");
    	   for(Book bb:books){
    		   System.out.println(bb);
    	   }
//    	   List<Book> list=dao.findAll();
//    	   for(Book b:list){
//    		   System.out.println(b);
//    	   }
       }
}

package dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.tedu.cloudnote.dao.BookDao;
import com.tedu.cloudnote.dao.RelationDao;
import com.tedu.cloudnote.entity.Book;
import com.tedu.cloudnote.entity.User;

import test.TestBase;

public class TestRelactionDao extends TestBase{
	 private RelationDao dao;
     @Before
     public void Init(){
  	 dao=super.getConText().getBean("relationDao",RelationDao.class);
     }
     @Test
     public void test1(){
    	User user= dao.findUserBooks(" 48595f52-b22c-4485-9244-f4004255b972".trim());
    	System.out.println("±Ê¼Ç±¾Êý£º"+user.getBooks().size());
    	List<Book> list=user.getBooks();
    	for(Book b:list){
    		System.out.println(b.getCn_notebook_name());
    	}
     }
     @Test
     public void test2(){
    	List<Book> list=dao.findBookUser();
    	for(Book b:list){
    		System.out.println("book:"+b.getCn_notebook_name()+"  user:"+b.getUser().getCn_user_name());
    	}
     }
}

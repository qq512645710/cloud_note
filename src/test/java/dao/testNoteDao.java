package dao;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.metadata.Db2CallMetaDataProvider;

import com.tedu.cloudnote.dao.BookDao;
import com.tedu.cloudnote.dao.NoteDao;
import com.tedu.cloudnote.dao.UserDao;
import com.tedu.cloudnote.entity.Book;
import com.tedu.cloudnote.entity.Note;
import com.tedu.cloudnote.entity.User;
import com.tedu.cloudnote.service.NoteService;
import com.tedu.cloudnote.util.NoteResult;

import test.TestBase;

public class testNoteDao extends TestBase{
	 private NoteDao dao;
	 private NoteService ns;
	 private UserDao ud;
	 private BookDao bd;
     @Before
     public void Init(){
  	 dao=super.getConText().getBean("noteDao",NoteDao.class);
  	 ns=super.getConText().getBean("noteService",NoteService.class);
  	 ud=super.getConText().getBean("userDao",UserDao.class);
  	 bd=super.getConText().getBean("bookDao",BookDao.class);
     }
     @Test
     public void test(){
    	 System.out.println(dao.findByBookId("4b86d1f9-6345-4532-bc50-ee86442f004b"));
    	 List<Note> list=dao.findAll();
    	 for(Note n:list){
    		 System.out.println(n);
    	 }
     }
     @Test
     public void test1(){
    	NoteResult<List<Map>> result= ns.LoadNotes("4b86d1f9-6345-4532-bc50-ee86442f004b");
    	 System.out.println(result.getStatus()+"\n"+result.getMsg()+"\n"+result.getData());
     }
     @Test
     public void test2(){
    	 List<Book> list1=bd.findAll();
    	 for(Book b:list1){
    		if(dao.findByBookId(b.getCn_notebook_id()).size()!=0){
    			if(dao.findByUserId(b.getCn_user_id()).size()!=0){
    				System.out.println(ud.findById(b.getCn_user_id()).getCn_user_name()+","+b.getCn_notebook_name());
    			}
    		}
    	 }
    	 
     }
     @Test
     public void test3(){
//    	 NoteResult<Note> r=ns.LoadNote("eb08f452-2bce-498c-b32c-75103252718b");
//    	 System.out.println(r);
    	Note note= dao.findById("a200ec50-4111-4785-97b3-539115b61ed5");
    	note.setCn_note_tittle("99999999999999999");
    	note.setCn_note_body("的风格环境");
    	int i=dao.Dupdate(note);
    	System.out.println(i);
     }
     @Test
     public void test4(){
    	 Map<String,Object> map=new HashMap<String, Object>();
//    	 map.put("title","%测试%");
//    	 map.put("status","2");
    	 Date begin=Date.valueOf("2016-09-01");
//    	 map.put("begin",begin.getTime());
    	 List<Note> list=dao.findNotes(map);
    	 for(Note n:list){
    		 System.out.println(n.getCn_note_title());
    	 }
    	 System.out.println("结果数："+list.size());
     }
     @Test
     public void test5(){
    	 String[] array={"fed920a0-573c-46c8-ae4e-368397846efd","ebd65da6-3f90-45f9-b045-782928a5e2c0"};
    	int rows= dao.deleteNotes(array);
    	 System.out.println(rows);
     }
}

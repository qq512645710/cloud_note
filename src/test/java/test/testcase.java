package test;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import com.sun.accessibility.internal.resources.accessibility;
import com.tedu.cloudnote.dao.UserDao;
import com.tedu.cloudnote.entity.User;
import com.tedu.cloudnote.service.UserService;
import com.tedu.cloudnote.util.NoteResult;
import com.tedu.cloudnote.util.NoteUtil;
import sun.misc.UCDecoder;

public class testcase {
	 private ApplicationContext ac;
	 UserService us;
	 UserDao dao;
	@Before
	public void init(){	
		String[] conf={"conf/spring-mybatis.xml","conf/spring-mvc.xml","conf/spring-transaction.xml"};
		ac=new ClassPathXmlApplicationContext(conf);
		us=ac.getBean("userService",UserService.class);
		dao=ac.getBean("userDao",UserDao.class);
	}
   @Test
   public void test() throws SQLException{
	   String[] conf={"conf/spring-mybatis.xml","conf/spring-mvc.xml"};
	   ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
//SqlSessionFactory ssf = ac.getBean("ssfb",SqlSessionFactory.class);
	   DataSource ds=ac.getBean("ds",DataSource.class);
	   System.out.println(ds.getConnection());
//	   System.out.println(ssf);
   }
   @Test//����UserDao����
//   @Transactional
   public void test1(){
	   User user=dao.findByName("pc");
	   if(user==null){
		   System.out.println("�û�������");
	   }else{
		   System.out.println("�û�����");
	   }
	   System.out.println(user.getCn_user_password());
	   System.out.println(us.getClass().getName());
   }
   @Test//����UserService  Ԥ�ڣ��������
   public void test2(){
	   NoteResult nResult=us.checkLogin("pc","");
	   System.out.println(nResult.getMsg());
   }
   @Test//����UserService  Ԥ�ڣ��û�������
   public void test3(){
	   NoteResult nResult=us.checkLogin("pc1","");
	   System.out.println(nResult.getMsg());
   }
   @Test//����UserService  Ԥ�ڣ���¼�ɹ�
   public void test4(){
	   NoteResult nResult=us.checkLogin("pc","123");
	   System.out.println(nResult.getMsg());
   }
   @Test//����UserService  Ԥ�ڣ���¼�ɹ�
   public void test5(){
//	   User user=new User();
//		user.setCn_user_id("13");	
//		user.setCn_user_name("abhxbj");
//		user.setCn_user_nick("fgthfht");
//		user.setCn_user_password("5468");
//		user.setCn_user_token("sdfgh");
//        dao.save(user);
        List<User> l=dao.findAll();
        for(User u:l){
        	System.out.println(u);
        }
   }
   @Test//����UserService  ע������ �û�����pc �ǳƣ�demo ���룺123456  Ԥ�ڣ��û����ѱ�ռ��
   public void test6(){
	NoteResult<Object> result=us.addUser("pc","demo","123456");
	System.out.println(result.getMsg()+"\n"+result.getStatus());
   }
   @Test//����UserService�û�����mfq �ǳƣ�m ���룺1234561  Ԥ�ڣ�ע��ɹ�
   public void test7(){
	NoteResult<Object> result=us.addUser("mfq","m","1234561");
	System.out.println(result.getMsg()+"\n"+result.getStatus());
	System.out.println(dao.findByName("mfq"));
   }
}

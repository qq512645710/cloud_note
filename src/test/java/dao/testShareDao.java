package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tedu.cloudnote.dao.ShareDao;
import com.tedu.cloudnote.entity.Share;

public class testShareDao {
         private ShareDao dao;
         @Before
         public void init(){
        	 String[] conf={"conf/spring-mybatis.xml","conf/spring-mvc.xml"};
        	 ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
        	 dao=ac.getBean("shareDao",ShareDao.class);
         }
         @Test//≤‚ ‘shareDaoπ¶ƒ‹
         public void test(){
        	 Map map=new HashMap<String,Object>();
        	 map.put("name","%s%");
        	 List<Share> list=dao.findByTitle(map);
        	 for(Share s:list){
        		 System.out.println(s);
        	 }
         }
}

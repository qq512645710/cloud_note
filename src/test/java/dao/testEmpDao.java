package dao;

import org.junit.Before;
import org.junit.Test;

import com.tedu.cloudnote.dao.EmpDao;
import com.tedu.cloudnote.dao.RelationDao;
import com.tedu.cloudnote.entity.Emp;

import test.TestBase;

public class testEmpDao  extends TestBase{
	private EmpDao dao;
    @Before
    public void Init(){
 	 dao=super.getConText().getBean("empDao",EmpDao.class);
    }
    @Test
    public void test(){
    	Emp emp=new Emp();
    	emp.setAge(20);
    	emp.setName("bob");
    	System.out.println(emp);
    	dao.save(emp);
    	System.out.println(emp);
    }
} 

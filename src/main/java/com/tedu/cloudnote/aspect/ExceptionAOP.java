package com.tedu.cloudnote.aspect;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//@Component
//@Aspect //ָ��Ϊ�������
public class ExceptionAOP {
	   //��e����������
       @AfterThrowing(throwing="e",pointcut="within(com.tedu.cloudnote.controller..*)")	
       public void logException(Exception e){
    	   //���쳣��Ϣд���ļ���
    	   try {
			PrintWriter pw=new PrintWriter(new FileWriter("D:\\error_log.txt",true));
			pw.println("***************************************");
			pw.println("*�쳣���ͣ�"+e);
			pw.println("*����ʱ�䣺"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			pw.println("**************�쳣����*******************");
			e.printStackTrace(pw);
			pw.close();
		} catch (IOException e1) {
			System.out.println("��¼�쳣ʧ�ܣ�");
		}
       }
}

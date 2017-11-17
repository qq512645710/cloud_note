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
//@Aspect //指定为切面组件
public class ExceptionAOP {
	   //将e当参数传入
       @AfterThrowing(throwing="e",pointcut="within(com.tedu.cloudnote.controller..*)")	
       public void logException(Exception e){
    	   //将异常信息写入文件中
    	   try {
			PrintWriter pw=new PrintWriter(new FileWriter("D:\\error_log.txt",true));
			pw.println("***************************************");
			pw.println("*异常类型："+e);
			pw.println("*发生时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			pw.println("**************异常详情*******************");
			e.printStackTrace(pw);
			pw.close();
		} catch (IOException e1) {
			System.out.println("记录异常失败！");
		}
       }
}

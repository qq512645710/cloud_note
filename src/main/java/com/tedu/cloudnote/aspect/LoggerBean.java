package com.tedu.cloudnote.aspect;

import java.lang.reflect.Proxy;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
/**
 * AOP:切面，切入点，通知，动态代理
 *  1.切面(Aspect)指封装了共同处理的组件,并且能够切入到其他目标组件方法上(带有@Aspect标记的Bean组件)
 *  2.切入点(Pointcut)用于指定目标组件及方法,spring提供了几种切入点表达式
 *    1.方法限定表达式  可指定某个组件中的部分方法追加功能   execution(修饰符？  返回类型  方法名(参数) 抛出异常？)
 *         例：execution(* add*(..))   匹配所有add开头的方法
 *            execution(* com.service.UserService.*(..))  匹配UserService下所有方法
 *    2.类型限定表达式   可指定某个组件中的所有方法追加功能    within(类.方法)   ..*表示类及其子包下所有方法
 *    3.bean类限定表达式                                                                 bean(id名)     
 *         例：bean(userService)   匹配名为userService组件的所有方法
 *            bean(*Service)      匹配所有以Service结尾的组件的所有方法
 *  3.通知(Advice)
 *  用于指定切入的时机，指定切面功能在什么时机插入到目标组件方法中
 *  Spring提供以下5种通知类型
 *        try{
 *          //1.使用前置通知<aop:before>
 *          方法1
 *          //2.使用后置通知<aop:after-returning>
 *        }catch(){
 *          //3.使用异常通知<aop:after-throwing>
 *        }finally{
 *          //4.使用最终通知<aop:after>
 *        }
 *            5.环绕通知:相当于前置+后置(即之前执行一个功能,之后再执行另一个功能)
 *   总结：
 *   切面：追加什么功能？
 *   切入点：切谁？
 *   通知：什么时候切？(时机)
 *  4.动态代理技术：可以动态创建类的技术
 *       有两种动态技术应用
 *            1.采用JDK java.reflect.Proxy API 可以根据指定接口生成代理类
 *            2.采用CGLIB包 可以根据指定的类生成代理类  
 *                public class 代理类  extends 目标类{
 *                     //重写父类方法
 *                }
 *      
 * @author lenovo  
 *
 */
//封装共同处理的组件
//@Component//扫描到容器,等价于<bean>定义
//@Aspect  //等价于<aop:aspect>定义
public class LoggerBean {
	   private int n=0;
	   @Before("within(com.tedu.cloudnote.controller..*)")
	   //等价于<aop:before>定义
	   //在Controller方法执行前，先执行logController处理
       public void logController(){
		   n++;
    	   System.out.println("进入Controller处理请求。。。"+n);
       }
	   @Before("execution(* com.tedu.cloudnote.service.UserServiceImpl.*(..))")
	   public void logService(){
		   n++;
		   System.out.println("进入service。。。"+n);
	   }
}

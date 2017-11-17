package com.tedu.cloudnote.aspect;

import java.lang.reflect.Proxy;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
/**
 * AOP:���棬����㣬֪ͨ����̬����
 *  1.����(Aspect)ָ��װ�˹�ͬ��������,�����ܹ����뵽����Ŀ�����������(����@Aspect��ǵ�Bean���)
 *  2.�����(Pointcut)����ָ��Ŀ�����������,spring�ṩ�˼����������ʽ
 *    1.�����޶����ʽ  ��ָ��ĳ������еĲ��ַ���׷�ӹ���   execution(���η���  ��������  ������(����) �׳��쳣��)
 *         ����execution(* add*(..))   ƥ������add��ͷ�ķ���
 *            execution(* com.service.UserService.*(..))  ƥ��UserService�����з���
 *    2.�����޶����ʽ   ��ָ��ĳ������е����з���׷�ӹ���    within(��.����)   ..*��ʾ�༰���Ӱ������з���
 *    3.bean���޶����ʽ                                                                 bean(id��)     
 *         ����bean(userService)   ƥ����ΪuserService��������з���
 *            bean(*Service)      ƥ��������Service��β����������з���
 *  3.֪ͨ(Advice)
 *  ����ָ�������ʱ����ָ�����湦����ʲôʱ�����뵽Ŀ�����������
 *  Spring�ṩ����5��֪ͨ����
 *        try{
 *          //1.ʹ��ǰ��֪ͨ<aop:before>
 *          ����1
 *          //2.ʹ�ú���֪ͨ<aop:after-returning>
 *        }catch(){
 *          //3.ʹ���쳣֪ͨ<aop:after-throwing>
 *        }finally{
 *          //4.ʹ������֪ͨ<aop:after>
 *        }
 *            5.����֪ͨ:�൱��ǰ��+����(��֮ǰִ��һ������,֮����ִ����һ������)
 *   �ܽ᣺
 *   ���棺׷��ʲô���ܣ�
 *   ����㣺��˭��
 *   ֪ͨ��ʲôʱ���У�(ʱ��)
 *  4.��̬�����������Զ�̬������ļ���
 *       �����ֶ�̬����Ӧ��
 *            1.����JDK java.reflect.Proxy API ���Ը���ָ���ӿ����ɴ�����
 *            2.����CGLIB�� ���Ը���ָ���������ɴ�����  
 *                public class ������  extends Ŀ����{
 *                     //��д���෽��
 *                }
 *      
 * @author lenovo  
 *
 */
//��װ��ͬ��������
//@Component//ɨ�赽����,�ȼ���<bean>����
//@Aspect  //�ȼ���<aop:aspect>����
public class LoggerBean {
	   private int n=0;
	   @Before("within(com.tedu.cloudnote.controller..*)")
	   //�ȼ���<aop:before>����
	   //��Controller����ִ��ǰ����ִ��logController����
       public void logController(){
		   n++;
    	   System.out.println("����Controller�������󡣡���"+n);
       }
	   @Before("execution(* com.tedu.cloudnote.service.UserServiceImpl.*(..))")
	   public void logService(){
		   n++;
		   System.out.println("����service������"+n);
	   }
}

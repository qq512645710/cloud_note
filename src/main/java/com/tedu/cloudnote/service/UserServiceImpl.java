package com.tedu.cloudnote.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tedu.cloudnote.dao.UserDao;
import com.tedu.cloudnote.entity.User;
import com.tedu.cloudnote.util.NoteResult;
import com.tedu.cloudnote.util.NoteUtil;
@Service("userService")//扫描到spring容器
@Transactional
public class UserServiceImpl  implements UserService{
    @Resource//注入dao对象
	private UserDao userDao;
	public NoteResult<User> checkLogin(String name, String password) {
		NoteResult result=new NoteResult();
        //检测用户名
		User user=userDao.findByName(name);
		if(user==null){
			result.setStatus(1);
			result.setMsg("用户名不存在！");
			return result;
		}
		//检测密码
		String MD5Password=NoteUtil.md5(password);
		System.out.println(MD5Password);
		if(!user.getCn_user_password().equals(MD5Password)){
			result.setStatus(2);
			result.setMsg("密码错误");
			return result;
		}
		//都正确
		result.setStatus(0);
		result.setMsg("登录成功");
		
		//创建令牌给用户
		String token=NoteUtil.createToken();
		user.setCn_user_token(token);
		userDao.updateToken(user);
		result.setData(user);
		return result;
	}
	public NoteResult<Object> addUser(String name, String nick, String password) {
		//执行用户名检测
		NoteResult<Object> result=new NoteResult<Object>();
		User hasUser=userDao.findByName(name);
		if(hasUser!=null){
		     result.setStatus(1);
		     result.setMsg("用户名已被占用");
		     return result;
		}
		//执行用户注册
		User user=new User();
		user.setCn_user_name(name);//设置用户名
		user.setCn_user_nick(nick);//设置昵称
		String md5password=NoteUtil.md5(password);
		user.setCn_user_password(md5password);
		user.setCn_user_id(NoteUtil.createId());//生成唯一id
		userDao.save(user);//保存user
		result.setStatus(0);
		result.setMsg("注册成功！");
		return result;
	}
       
}

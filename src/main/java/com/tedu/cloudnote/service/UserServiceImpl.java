package com.tedu.cloudnote.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tedu.cloudnote.dao.UserDao;
import com.tedu.cloudnote.entity.User;
import com.tedu.cloudnote.util.NoteResult;
import com.tedu.cloudnote.util.NoteUtil;
@Service("userService")//ɨ�赽spring����
@Transactional
public class UserServiceImpl  implements UserService{
    @Resource//ע��dao����
	private UserDao userDao;
	public NoteResult<User> checkLogin(String name, String password) {
		NoteResult result=new NoteResult();
        //����û���
		User user=userDao.findByName(name);
		if(user==null){
			result.setStatus(1);
			result.setMsg("�û��������ڣ�");
			return result;
		}
		//�������
		String MD5Password=NoteUtil.md5(password);
		System.out.println(MD5Password);
		if(!user.getCn_user_password().equals(MD5Password)){
			result.setStatus(2);
			result.setMsg("�������");
			return result;
		}
		//����ȷ
		result.setStatus(0);
		result.setMsg("��¼�ɹ�");
		
		//�������Ƹ��û�
		String token=NoteUtil.createToken();
		user.setCn_user_token(token);
		userDao.updateToken(user);
		result.setData(user);
		return result;
	}
	public NoteResult<Object> addUser(String name, String nick, String password) {
		//ִ���û������
		NoteResult<Object> result=new NoteResult<Object>();
		User hasUser=userDao.findByName(name);
		if(hasUser!=null){
		     result.setStatus(1);
		     result.setMsg("�û����ѱ�ռ��");
		     return result;
		}
		//ִ���û�ע��
		User user=new User();
		user.setCn_user_name(name);//�����û���
		user.setCn_user_nick(nick);//�����ǳ�
		String md5password=NoteUtil.md5(password);
		user.setCn_user_password(md5password);
		user.setCn_user_id(NoteUtil.createId());//����Ψһid
		userDao.save(user);//����user
		result.setStatus(0);
		result.setMsg("ע��ɹ���");
		return result;
	}
       
}

package com.tedu.cloudnote.service;
import com.tedu.cloudnote.entity.User;
import com.tedu.cloudnote.util.NoteResult;
public interface UserService {
	//public ��������  ������(���������������)
	  public NoteResult<User> checkLogin(String name,String password);
	  public NoteResult<Object> addUser(String name,String nick,String password);
}
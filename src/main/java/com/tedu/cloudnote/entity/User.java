package com.tedu.cloudnote.entity;

import java.io.Serializable;
import java.util.List;
//属性名与字段名一致，类型一致
//实现序列化接口
public class User implements Serializable {
       private String cn_user_name;
       private String cn_user_id;
       private String cn_user_password;
       private String cn_user_token;
       private String cn_user_nick;
       //追加相关Book关联属性
       private List<Book> Books;
       public List<Book> getBooks() {
		return Books;
	}
	public void setBooks(List<Book> books) {
		Books = books;
	}
	@Override
	public String toString() {
		return "User [cn_user_name=" + cn_user_name + ", cn_user_id=" + cn_user_id + ", cn_user_password="
				+cn_user_password+", cn_user_token="+cn_user_token+", cn_user_nick="+cn_user_nick + "]";
	}
	public String getCn_user_name() {
		return cn_user_name;
	}
	public void setCn_user_name(String cn_user_name) {
		this.cn_user_name = cn_user_name;
	}
	public String getCn_user_id() {
		return cn_user_id;
	}
	public void setCn_user_id(String cn_user_id) {
		this.cn_user_id = cn_user_id;
	}
	public String getCn_user_password() {
		return cn_user_password;
	}
	public void setCn_user_password(String cn_user_password) {
		this.cn_user_password = cn_user_password;
	}
	public String getCn_user_token() {
		return cn_user_token;
	}
	public void setCn_user_token(String cn_user_token) {
		this.cn_user_token = cn_user_token;
	}
	public String getCn_user_nick() {
		return cn_user_nick;
	}
	public void setCn_user_nick(String cn_user_nick) {
		this.cn_user_nick = cn_user_nick;
	}
}

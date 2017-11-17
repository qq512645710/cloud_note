package com.tedu.cloudnote.entity;

import java.io.Serializable;

public class Emp  implements Serializable{
     private int id;
     private String name;
     private int age;
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Emp [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}

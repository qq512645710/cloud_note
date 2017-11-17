package com.tedu.cloudnote.util;

import java.io.Serializable;
//jsonÊµÌåÀà{"status":xx,"msg":xxx,"data":xxx}
public class NoteResult<T> implements Serializable {
   @Override
	public String toString() {
		return "NoteResult [status=" + status + ", msg=" + msg + ", data=" + data + "]";
	}
/**
	 * 
	 */
	public NoteResult() {
		// TODO Auto-generated constructor stub
	}
public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
   private int status;
   private String msg;
   private T data;
   
}

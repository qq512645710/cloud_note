package com.tedu.cloudnote.util;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
public class NoteUtil {
	/**
	 * 密码加密处理(MD5)
	 * @param src 原密码
	 * @return  加密后的内容
	 * @throws GeneralSecurityException 
	 */
      public static String md5(String src) {
    	  byte[] output=null;
    	  String out=null;
    	  try {
			MessageDigest mdDigest=MessageDigest.getInstance("MD5");
			output=mdDigest.digest(src.getBytes());//加密处理
			out=Base64.encodeBase64String(output);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NoteException("密码加密失败",e); 
		}    	  
    	  //将加密结果output转成字符串输出
		return out;
      }
      //利用uuid算法生成一个主键值
      public static String createId(){
    	UUID uuid=UUID.randomUUID();
    	String idString=uuid.toString();
    	idString=idString.replace("-","");
		return idString;
      }
      public static String createToken(){
    	  return createId();
      }
}

package com.tedu.cloudnote.util;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
public class NoteUtil {
	/**
	 * ������ܴ���(MD5)
	 * @param src ԭ����
	 * @return  ���ܺ������
	 * @throws GeneralSecurityException 
	 */
      public static String md5(String src) {
    	  byte[] output=null;
    	  String out=null;
    	  try {
			MessageDigest mdDigest=MessageDigest.getInstance("MD5");
			output=mdDigest.digest(src.getBytes());//���ܴ���
			out=Base64.encodeBase64String(output);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NoteException("�������ʧ��",e); 
		}    	  
    	  //�����ܽ��outputת���ַ������
		return out;
      }
      //����uuid�㷨����һ������ֵ
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

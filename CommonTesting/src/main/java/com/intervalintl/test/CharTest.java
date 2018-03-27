package com.intervalintl.test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class CharTest {
   public static void main(String[] args) throws UnsupportedEncodingException {

	   System.out.println("s".getBytes().length);  
	   System.out.println("s".getBytes("GBK").length); 
	   System.out.println("s".getBytes("gb2312").length); 
	   System.out.println("s".getBytes("utf-16be").length); 
	   System.out.println("s".getBytes("utf-16").length); 
	   
	   String s="名称";
	   
	   System.out.println(s.getBytes().length);
	   System.out.println(s.getBytes("GB2312").length);
	   
	   System.out.println(new String(s.getBytes("GB2312"), "GB2312"));
	   System.out.println(new String(s.getBytes("utf-8"), "GB2312"));
	   System.out.println(new String(s.getBytes("utf-8"), "GBK"));
	   System.out.println(new String(s.getBytes("utf-16BE"), "GBK"));
	   System.out.println(new String(s.getBytes("iso8859_1"), "iso8859_1"));
	   
	   System.out.println(new String(s.getBytes("ISO-8859-1"),"gb2312")); 
	   System.out.println(new String(s.getBytes("UTF8"),"gb2312"));	 
	   System.out.println(new String(s.getBytes("GBK"),"gb2312")); 
	   System.out.println(new String(s.getBytes("BIG5"),"gb2312"));
	   
	   System.out.println(new String(s.getBytes("GBK"),"GBK")); 
	   System.out.println(new String(s.getBytes("BIG5"),"BIG5"));
	   
	   System.out.println(s.getBytes("ISO8859_1").length);
	   
	 
	   
	   Test1.charset();
 }
   
   public static void charset(){
		 System.out.println(Charset.defaultCharset()); 
	}
}

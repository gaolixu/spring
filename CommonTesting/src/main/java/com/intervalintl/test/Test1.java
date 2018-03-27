package com.intervalintl.test;

import java.nio.charset.Charset;

public class Test1 {

	public static void main(String[] args) {
		 System.out.println("RESORT           &amp; AREA".replace("&amp;", " ").replaceAll("\\s+", "_"));
		 System.out.println("当前JVM的默认字符集：" + Charset.defaultCharset()); 
		 
		 CharTest.charset();

	}
	
	public static void charset(){
		 System.out.println("当前JVM的默认字符集：" + Charset.defaultCharset()); 
	}

}

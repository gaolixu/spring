package com.intervalintl.files;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertyFile {

	
	private static final String PROPERTY_FILE = "Y:/NAS/DEV/BATCH/data/DEV2/PositivePay/positivePay.properties";   
	 
	 
	  public String readData(String key) {   
	         Properties props = new Properties();   
	         try {   
	             InputStream in = new BufferedInputStream(new FileInputStream(PROPERTY_FILE));   
	             props.load(in);   
	             in.close();   
	             String value = props.getProperty(key);   
	             return value;   
	         } catch (Exception e) {   
	             e.printStackTrace();   
	             return null;   
	         }   
	     }   
	  
	  public void writeData(String key, String value) {   
	        Properties prop = new Properties();   
	        try {   
	            File file = new File(PROPERTY_FILE);   
	            if (!file.exists())   
	                file.createNewFile();   
	            InputStream fis = new FileInputStream(file);   
	            prop.load(fis);   
	            fis.close();//一定要在修改值之前关闭fis   
	            OutputStream fos = new FileOutputStream(PROPERTY_FILE);   
	            prop.setProperty(key, value);   
	            prop.store(fos, "Updated " + key + "'s value");   
	            //prop.store(fos, "just for test"); 
	           
	            
	            fos.close();   
	        } catch (IOException e) {   
	            System.err.println("Visit " + PROPERTY_FILE + " for updating "  
	                    + value + " value error");   
	        }
	    }
	  
	  public static void main(String[] args) {
		  // TODO Auto-generated method stub
		  PropertyFile test = new PropertyFile();
		  test.writeData("name", "xiaozhang");
		  test.writeData("sex", "male");
		  test.writeData("sex two", "male");
		  
		//  test.writeData("name", "xiaoyang");
		  
		  String name = test.readData("name");
		  String sex = test.readData("sex");
		  System.out.println("The name of the person is:" + name + ", and the sex is:" + sex);
		  
		  test.writeData("name", "xiaozhang222");
		  test.writeData("sex", "male222");
		  test.writeData("test", "test");
		  
		   name = test.readData("name");
		   sex = test.readData("sex");
		  System.out.println("The name of the person is:" + name + ", and the sex is:" + sex + "sex two :" +test.readData("sex two") );
		  
		  
		 }
}

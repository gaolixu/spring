package com.intervalintl.test;

public class GenericObject extends GenericParentTest<Object>{

	public <T> void print(T x) {  
        System.out.println(x.getClass().getName());  
    }  
	
	public  void print(Integer x) {  
        System.out.println("Integer"+x);  
    }  

    public static void main(String[] args) {  
    	GenericObject method = new GenericObject();  
        method.print(" ");  
        method.print(10);  
        method.print(Integer.valueOf(100)); 
        method.print('a');  
        method.print(method);  
        
        
    }  
}

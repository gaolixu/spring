package com.intervalintl.test;

public class ThisTest {
	public static void main(String[] args) { 
        new C(); 
    } 
}


class F{ 
    public F() { 
    	 System.out.println(this);
        System.out.println(this.getClass().getName()); 
        this.hello(); 
        this.hello2();
    } 
    private void hello() { 
        System.out.println(1234); 
    }  
    
    public void hello2() { 
        System.out.println(2222); 
    } 
} 

class C extends F{ 
    public C() { 
         System.out.println(super.getClass().getName());
         System.out.println(this);
    } 
     
    public void hello() { 
        System.out.println(12345678); 
    } 
    
    public void hello2() { 
        System.out.println(2222222); 
    }
}
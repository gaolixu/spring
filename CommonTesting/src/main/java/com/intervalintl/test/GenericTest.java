package com.intervalintl.test;

import java.util.ArrayList;
import java.util.List;

public class GenericTest extends GenericParentTest<GenericObject>{

	public <T> void print(T x) {  
        System.out.println(x.getClass().getName());  
    }  
	
	public  void print(Integer x) {  
        System.out.println("Integer"+x);  
    }  
	
    public void write(List<? extends GenericObject> items) throws Exception {
		
	}
    
    public void write222(List<? extends Fruit> items) throws Exception {
		//items.add(new Apple());
   	}
    
    
   public void foo(List<Object> list, Object o){
	   //list.add(Integer.valueOf(100));
	   
   }

    public static <T> void main(String[] args) {  
    	GenericTest method = new GenericTest();  
        method.print(" ");  
        method.print(10);  
        method.print(Integer.valueOf(100)); 
        method.print('a');  
        method.print(method);  
        
        List<Integer> integers = new ArrayList<Integer>();
       // method.foo(integers, "dd");
        
        List<T> fruitlist111 = (List<T>) new ArrayList<Fruit>(0);
        List<? extends Fruit> fruitlist = new ArrayList<Fruit>(0);
        List<Apple> applelist = new ArrayList<Apple>(0);
       // List<Apple> applelist2 = new ArrayList<Fruit>(0);
     //   Apple app= (Apple) new Fruit();
        fruitlist = applelist;
        Fruit apple = new Apple();
       // fruitlist.add(apple);
        
        List<?> lists = new ArrayList<String>();
       // lists.add(new Object());
        
        List<Fruit> fruitlist333 = new ArrayList<Fruit>(0);
       // fruitlist333 = applelist;
        
        List<?> fruits = new ArrayList<Apple>();
        
        List<Fruit> fruitlist444=(List<Fruit>) fruits;
        fruitlist444.add(new Orange());
        //fruitlist444.add(new Orange());
        
    }  
}

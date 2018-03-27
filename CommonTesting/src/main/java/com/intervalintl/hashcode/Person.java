package com.intervalintl.hashcode;

public class Person {
	 /** 
     * 每一个成人都有一个身份证 
     */  
    private Code code;  
  
    /** 
     * 姓名 
     */  
    private String name;  
  
    public Code getCode() {  
        return code;  
    }  
  
    public void setCode(Code code) {  
        this.code = code;  
    }  
  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
  
    public Person() {  
  
    }  
  
    public Person(Code code, String name) {  
        this.code = code;  
        this.name = name;  
    }  
  
    /** 
     * 重写equals()方法 当两个人得身份证号相同以及姓名相同时，表示这两个人是同一个人。 
     */  
    public boolean equals(Object o) {  
        if (o == this) {  
            return true;  
        }  
        if (o instanceof Person) {  
            Person p = (Person) o;  
            boolean b = this.code.equals(p.code) && this.name.equals(p.name);  
            return b;  
        }  
        return false;  
    }  
  
    /** 
     * 重写toString()方法 
     */  
    public String toString() {  
        return "【姓名】：" + name + "  ";  
    }  
}

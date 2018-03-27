package com.intervalintl.test;

public class StaticAndConstructorTest {

	public static void main(String[] args) {
		B b = new B();
		b.methodA();

	}

}


class A {
	
	
	int a1 = 8;
	int a2=getA2();
	{
		
		int  a3=9;
		System.out.println("top of A() a1="+a1 + " a2=" + a2+" a3="+a3);
	}
	
	public A(){
		this(66);
		System.out.println("A constructor\n");
	}
	
	{
		System.out.println("below A() .. has start");
	}
	
	public A(int num){
		System.out.println("A constructor with :" +num +"\n" );
	}
	
	static{
		System.out.println("I am a static {} from clas A.");
	}
	
	int getA2(){
		System.out.println("getA2 ...");
	   return 7;
	}
	
	public void methodA(){
		System.out.println("methodA");
	}
	
}
   
class B extends A{
	int b1=0;
	int b2=getB2();
     {
		
		int  b3=5;
		System.out.println("top of B() b1="+b1 + " b2=" + b2+" b3="+b3);
	}
	
	public B(){
		this(33);
		//super(44);
		System.out.println("B constructor\n");
	}
	
	
	public B(int num){
		System.out.println("B constructor with :" +num +"\n" );
	}
	
	{
		System.out.println("below B() .. has start");
	}
	static{
		System.out.println("I am a static {} from clas B.");
	}
	
	int getB2(){
		System.out.println("getB2 ...");
	   return 33;
	}
	
	public void methodA(){
		System.out.println("methodA int calss B");
		super.methodA();
	}
	
	
}


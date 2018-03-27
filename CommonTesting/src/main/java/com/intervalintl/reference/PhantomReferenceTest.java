package com.intervalintl.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Field;

public class PhantomReferenceTest {
	public void testReference() {  
        
    }  
	
	public static void main(String[] args) throws Exception {
		phantom2();
	}
	
	public static void phantom1() throws Exception  
	{ 
		Object object = new Object();  
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<Object>();  
        Reference<Object> reference = new PhantomReference<Object>(object,  
                referenceQueue);  
        System.out.println(reference);  
        System.out.println(reference.get());  
        System.out.println(reference.isEnqueued());  
        object = null;  
        System.gc();  
        System.out.println(reference.isEnqueued());  
        try {  
        	Thread.sleep(2000L);
        	 System.out.println(referenceQueue.poll()); 
            System.out.println(referenceQueue.remove(2000));  
        } catch (IllegalArgumentException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (InterruptedException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
	}
	
	public static void phantom2() throws Exception  
	{  
	    Object obj = new Object();  
	    ReferenceQueue<Object> refQueue = new ReferenceQueue<Object>();  
	    PhantomReference<Object> phantom = new PhantomReference<Object>(obj,  
	            refQueue);  
	    System.out.println(phantom.get()); // java.lang.Object@f9f9d8  
	    System.out.println(refQueue.poll());// null  
	  
	    obj = null;  
	    System.gc();  
	  
	    // 调用phanRef.get()不管在什么情况下会一直返回null  
	    System.out.println(phantom.get());  
	  
	    // 当GC发现了虚引用，GC会将phanRef插入进我们之前创建时传入的refQueue队列  
	    // 注意，此时phanRef所引用的obj对象，并没有被GC回收，在我们显式地调用refQueue.poll返回phanRef之后  
	    // 当GC第二次发现虚引用，而此时JVM将phanRef插入到refQueue会插入失败，此时GC才会对obj进行回收  
	    Thread.sleep(2000);  
	    System.gc();  
	    System.out.println(refQueue.poll());  
	    System.out.println(refQueue.remove(3000));  
	}  
	
	public static void phantom3() throws InterruptedException{
		String abc = new String("abc");
        System.out.println(abc.getClass() + "@" + abc.hashCode());
        final ReferenceQueue<String> referenceQueue = new ReferenceQueue<String>();
        new Thread() {
            public void run() {
            	boolean isRun = true;
                while (isRun) {
                    Object obj = referenceQueue.poll();
                    if (obj != null) {
                        try {
                            Field rereferent = Reference.class
                                    .getDeclaredField("referent");
                            rereferent.setAccessible(true);
                            Object result = rereferent.get(obj);
                            System.out.println("gc will collect："
                                    + result.getClass() + "@"
                                    + result.hashCode() + "\t"
                                    + (String) result);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        isRun = false;
                    }
                }
            }
        }.start();
        PhantomReference<String> abcWeakRef = new PhantomReference<String>(abc,
                referenceQueue);
        abc = null;
        Thread.currentThread().sleep(3000);
        System.gc();
        Thread.currentThread().sleep(3000);
       
    }
	
}

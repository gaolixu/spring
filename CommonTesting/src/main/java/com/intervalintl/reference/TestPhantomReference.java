package com.intervalintl.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Field;

public class TestPhantomReference {
	private static volatile boolean isRun = true;  
	  
    private static volatile ReferenceQueue<String> referenceQueue = new ReferenceQueue<String>();  
  
    public static void main(String[] args) throws Exception  
    {  
        String abc = new String("abc");  
        System.out.println(abc.getClass() + "@" + abc.hashCode());  
  
        new Thread() {  
            public void run()  
            {  
                while (isRun)  
                {  
                    Object o = referenceQueue.poll();  
                    if (o != null)  
                    {  
                        try  
                        {  
                            Field rereferent = Reference.class  
                                    .getDeclaredField("referent");  
                            rereferent.setAccessible(true);  
                            Object result = rereferent.get(o);  
                            System.out.println("gc will collect:"  
                                    + result.getClass() + "@"  
                                    + result.hashCode());  
                        } catch (Exception e)  
                        {  
                            e.printStackTrace();  
                        }  
                    }  
                }  
            }  
        }.start();  
  
        // 测试情况1:对象是虚可达的  
        PhantomReference<String> phantom = new PhantomReference<String>(abc,  
                referenceQueue);  
        System.out.println("phantom=" + phantom);  
  
        // 测试情况2:对象是不可达的,直接就被回收了,不会加入到引用队列  
        // new PhantomReference<String>(abc, referenceQueue);  
  
        // 清除强引用,触发GC  
        abc = null;  
        System.gc();  
  
        Thread.sleep(3000);  
        isRun = false;  
    }  
}

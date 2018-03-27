package com.intervalintl.test;

import java.util.ArrayList;
import java.util.List;

public class FinallyReturn {
    boolean testEx() throws Exception {
        boolean ret = true;
        try {
            ret = testEx1();
        } catch (Exception e) {
            System.out.println("testEx, catch exception");
            ret = false;
            throw e;
        } finally {
            System.out.println("testEx, finally; return value=" + ret);
            return ret;
        }
    }

    boolean testEx1() throws Exception {
        boolean ret = true;
        try {
            ret = testEx2();
            if (!ret) {
                return false;
            }
            System.out.println("testEx1, at the end of try");
            return ret;
        } catch (Exception e) {
            System.out.println("testEx1, catch exception");
            ret = false;
            throw e;
        } finally {
            System.out.println("testEx1, finally; return value=" + ret);
            return true;
        }
    }

    boolean testEx2() throws Exception {
        boolean ret = true;
        try {
            int b = 12;
            int c;
            for (int i = 2; i >= -2; i--) {
                c = b / i;
                System.out.println("i=" + i);
            }
            return true;
        } catch (Exception e) {
            System.out.println("testEx2, catch exception");
            ret = false;
            throw e;
        } finally {
            System.out.println("testEx2, finally; return value=" + ret);
            return ret;
        }
    }

   /* public static void main(String[] args) {
    	FinallyReturn testException1 = new FinallyReturn();
        try {
            testException1.testEx();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    
    
    public  int x = 3;
    TestVO test1= new TestVO();
    int testEx3() throws Exception {
        try {
        	int ii=0;
           x= x / ii;
           //return x;
        } catch (Exception e) {
        	x=4;
        	throw e;
           // return x;
        } finally {
            x = 5;
            return 6;
        }
      //  return x;
    }
    
    TestVO testEx4() throws Exception {
    	TestVO test0= new TestVO();
        try {
        	test1.setName("test4");
            //return test1;
        	int i=0;
        	int ii=6;
        	i= ii/i;
        } catch (Exception e) {
        	test0.names.add("0000");
        	test0.setName("0000000");   
        	throw e;
        } finally {
        	//TestVO test5= new TestVO();
        	test1.setName("finally");
        	test0.names.add("111111111");
            //return test1;
        }
        return test0;
    }
    public static void main(String[] args) {
    	FinallyReturn testException3 = new FinallyReturn();
        try {
            int a = testException3.testEx3();
            System.out.println(a);
            //System.out.println(x);
        } catch (Exception e) {
        	System.out.println("eeeeeeeeeeeeeeeeeeeeeee");
        }
        
        try {
        	TestVO a = testException3.testEx4();
            System.out.println(a.getName());
            System.out.println(a.names);
        } catch (Exception e) {
        	System.out.println("eeeeeeeeeeeeeeeeeeeeeee22222222222222");
        }
    }
    
    class TestVO{
    	List<String> names = new ArrayList<String>(0);
    	String name= "test1";

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
    	
    	
    }
}

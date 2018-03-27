package com.cmcc.pboss;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/Mock")
public class PbossRestController {

	    @RequestMapping(value="/cookie", method = RequestMethod.GET)
	    public void cookie(HttpServletRequest request,HttpServletResponse response) throws IOException{
	    	
	    	HttpSession hs = request.getSession(false);
	    	System.out.println(hs);
	    	
	    	/*Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组  
            if (null==cookies) {  
                System.out.println("No cookie---------------");  
                Cookie cookie = new Cookie("CookieTest", "CookieTestValue");  
                cookie.setMaxAge(30 * 60);// 设置为30min  
                cookie.setPath("/");  
                System.out.println("CookieTest added");  
                response.addCookie(cookie);  
            } else {  
                for(Cookie cookie : cookies){  
                    System.out.println("name:"+cookie.getName()+",value:"+ cookie.getValue());  
                }  
                
                for(Cookie cookie : cookies){  
                	System.out.println("Delete cookie:"+cookie.getName());  
                        cookie.setValue(null);  
                        cookie.setMaxAge(0);// 立即销毁cookie  
                        cookie.setPath("/");  
                        response.addCookie(cookie);  
                } 
            }  */
	    	
	       String ua = request.getHeader("User-Agent");
	        
	       PrintWriter writer = response.getWriter();
	       writer.write("User Agent is :" +ua);
	       writer.flush();
	    }
	    
	    
	    @RequestMapping(value = "/cookie2", method = RequestMethod.GET)
	    public @ResponseBody   MyObject doStuff(HttpSession session)
	    {
	            session.setAttribute("foo", "bar");
	            return  new MyObject();
	    }
	    
	    @RequestMapping(value = "/cookie3", method = RequestMethod.GET)
	    public @ResponseBody   MyObject doStuff3()
	    {
	            return  new MyObject();
	    }
}

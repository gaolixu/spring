package com.symbio.mock;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@RequestMapping("/Mock")
public class MockController {
	 
	  @GetMapping("/console")
	  public String console() {
	    return "console";
	  }
	 
	  @PostMapping("/startMock")
	  public @ResponseBody Map<String, Object> postData(String method,  String action) {
	    System.out.println("method:" + method);
	    System.out.println(action);
	    Map<String, Object> map = new HashMap<>();
	    map.put("result", "true");
	   
	    return map;
	  }
	 
	  @PostMapping("/mockAllnet")
	  public @ResponseBody Map<String, Object> mockAllnet(@RequestBody Mock mock) {
		 System.out.println("method:" + mock.getMethod());
		 System.out.println("action:" + mock.getAction());
		 
		 Map<String, Object> map = new HashMap<>();
		 if(mock.getAction().equalsIgnoreCase("start")){
			 if( MockServiceUtils.startMockAllnetService(mock.getMethod())){
				 map.put("result", "started");
			 }else{
				 map.put("result", "failed");
			 }			 
		 }else {
			 if( MockServiceUtils.stopMockAllnetService()){
				 map.put("result", "stopped");
			 }else{
				 map.put("result", "failed");
			 }
		 }
		 
	    return map;
	  }
	 
	  
	  @PostMapping("/mockHlr")
	  public @ResponseBody Map<String, Object> mockHlr(@RequestBody Mock mock) {
		 System.out.println("method:" + mock.getMethod());
		 System.out.println("action:" + mock.getAction());
		 
		 Map<String, Object> map = new HashMap<>();
		 if(mock.getAction().equalsIgnoreCase("start")){
			 if( MockServiceUtils.startMockHlrService(mock.getMethod())){
				 map.put("result", "started");
			 }else{
				 map.put("result", "failed");
			 }			 
		 }else {
			 if( MockServiceUtils.stopMockHlrService()){
				 map.put("result", "stopped");
			 }else{
				 map.put("result", "failed");
			 }
		 }
		 
	    return map;
	  }
	 
	 
	    
}

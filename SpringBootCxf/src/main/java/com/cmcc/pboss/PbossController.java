package com.cmcc.pboss;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmcc.services.pboss.pbossservice.InterBossRequest;
import com.cmcc.services.pboss.pbossservice.InterBossResponse;

@Controller
//@RequestMapping("/Mock")
public class PbossController {
	 
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
	 
	  @PostMapping("/runTest")
	  public @ResponseBody Map<String, Object> runTest(@RequestBody RequestObject data) {
		 System.out.println("Reqeust is :  ---------------  "+data);
		 InterBossRequest interBossRequest= XmlBeanConverter.convertToJavaBean(data.getRequestString(), InterBossRequest.class);
		 
		 
		 
		 Map<String, Object> map = new HashMap<>();
		 InterBossResponse interBossResponse = new InterBossResponse();
			interBossResponse.setOprSeq("2001BIP2B96420160317121235010004");
			interBossResponse.setRspCode("11001");
			interBossResponse.setRspDesc("SUCCESS0001: PersonOrderInfo is synced");
		 map.put("result",XmlBeanConverter.convertToXml(interBossResponse));
	 
 
       return map;
	  }
	 
	  
	 
	 
	 
	    
}

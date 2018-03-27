package com.symbio.mock;
import java.io.File;

import org.apache.commons.io.FileUtils;

import com.eviware.soapui.config.MockOperationDispatchStyleConfig;
import com.eviware.soapui.impl.WsdlInterfaceFactory;
import com.eviware.soapui.impl.wsdl.WsdlInterface;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.mock.WsdlMockOperation;
import com.eviware.soapui.impl.wsdl.mock.WsdlMockResponse;
import com.eviware.soapui.impl.wsdl.mock.WsdlMockService;

public class MockServiceUtils {
	public static void main(String[] args) throws Exception {
		
		/* putFactory(MockOperationDispatchStyleConfig.SEQUENCE.toString(),
					new SequenceMockOperationDispatcher.Factory());
			 30  putFactory(MockOperationDispatchStyleConfig.RANDOM.toString(),
					new RandomMockOperationDispatcher.Factory());
			 31  putFactory(MockOperationDispatchStyleConfig.SCRIPT.toString(),
					new ScriptMockOperationDispatcher.Factory());
			 32  putFactory(MockOperationDispatchStyleConfig.XPATH.toString(),
					new XPathMockOperationDispatcher.Factory());
			 33  putFactory(MockOperationDispatchStyleConfig.QUERY_MATCH.toString(),*/
		
		WsdlProject project = new WsdlProject();
		WsdlInterface iface = WsdlInterfaceFactory.importWsdl(project, "file:/F:/mock/AllnetMockService.wsdl", true)[0];
		WsdlMockService service = project.addNewMockService("allnetService");
		service.setPort(8089);
		service.setPath("/mock");
		WsdlMockOperation mockOperation = (WsdlMockOperation) service.addNewMockOperation(iface.getOperationByName("syncUserInfo"));
		
		mockOperation.setDispatchStyle(MockOperationDispatchStyleConfig.SCRIPT.toString());
		mockOperation.setScript(FileUtils.readFileToString(new File("F:/mock/allnet_syncUserInfo.groovy")));
		
		WsdlMockResponse response = mockOperation.addNewMockResponse("Success", true);
		response.setResponseContent(FileUtils.readFileToString(new File("F:/mock/allnetService_Success_Response.xml")));
		
		WsdlMockResponse response2 = mockOperation.addNewMockResponse("Fail", true);
		response2.setResponseContent(FileUtils.readFileToString(new File("F:/mock/allnetService_Fail_Response.xml")));
		service.start();
		System.out.println(response.getResponseContent());
	}
	
 
  
  static WsdlProject projectAllnet = null;
  static WsdlInterface ifaceAllnet = null;
  static WsdlMockService serviceAllnet=null;
  static WsdlMockOperation allnetSyncUserInfo=null;
  
   public static boolean startMockAllnetService(String method)   {
	   try{	
		if(projectAllnet==null){
			projectAllnet = new WsdlProject();
		}
		
		if(ifaceAllnet==null){
			ifaceAllnet	= WsdlInterfaceFactory.importWsdl(projectAllnet, "file:/F:/mock/AllnetMockService.wsdl", true)[0];
		}
		
		//if(serviceAllnet==null){
			serviceAllnet = projectAllnet.addNewMockService("allnetService");
			serviceAllnet.setPort(8089);
			serviceAllnet.setPath("/mock");
		//}
		 allnetSyncUserInfo = (WsdlMockOperation) serviceAllnet.addNewMockOperation(ifaceAllnet.getOperationByName("syncUserInfo"));
		
		//public static final Enum RANDOM = Enum.forString("RANDOM");
		/*  25  public static final Enum SEQUENCE = Enum.forString("SEQUENCE");
		
		  27  public static final Enum SCRIPT = Enum.forString("SCRIPT");*/
		if(method.equalsIgnoreCase("success")){
			allnetSyncUserInfo.setDispatchStyle(MockOperationDispatchStyleConfig.SCRIPT.toString());
			allnetSyncUserInfo.setScript("return 'Success'");
		}else if (method.equalsIgnoreCase("fail")){
			allnetSyncUserInfo.setDispatchStyle(MockOperationDispatchStyleConfig.SCRIPT.toString());
			allnetSyncUserInfo.setScript("return 'Fail'");
		}else if (method.equalsIgnoreCase("order")){
			allnetSyncUserInfo.setDispatchStyle(MockOperationDispatchStyleConfig.SEQUENCE.toString());
		}else if (method.equalsIgnoreCase("random")){
			allnetSyncUserInfo.setDispatchStyle(MockOperationDispatchStyleConfig.RANDOM.toString());
		}else if (method.equalsIgnoreCase("script")){
			allnetSyncUserInfo.setDispatchStyle(MockOperationDispatchStyleConfig.SCRIPT.toString());
			allnetSyncUserInfo.setScript(FileUtils.readFileToString(new File("F:/mock/allnet_syncUserInfo.groovy")));
		}
		WsdlMockResponse response = allnetSyncUserInfo.addNewMockResponse("Success", true);
		response.setResponseContent(FileUtils.readFileToString(new File("F:/mock/allnetService_Success_Response.xml")));
		
		WsdlMockResponse response2 = allnetSyncUserInfo.addNewMockResponse("Fail", true);
		response2.setResponseContent(FileUtils.readFileToString(new File("F:/mock/allnetService_Fail_Response.xml")));
		serviceAllnet.start();
	   }catch(Exception e){
		   e.printStackTrace();
		   return false;
	   }
		return true;
	}
   
   public static boolean stopMockAllnetService() {
       try{		
    	   serviceAllnet.release();
       }catch(Exception e){
    	   return false;
       }
		return true;
	}
   
   
   static WsdlProject projectHlr = null;
   static WsdlInterface ifaceHlr = null;
   static WsdlMockService serviceHlr=null;
   static WsdlMockOperation hlrSyncUserInfo=null;
   
    public static boolean startMockHlrService(String method)   {
 	   try{	
 		if(projectHlr==null){
 			projectHlr = new WsdlProject();
 		}
 		
 		if(ifaceHlr==null){
 			ifaceHlr	= WsdlInterfaceFactory.importWsdl(projectHlr, "file:/F:/mock/HlrMockService.wsdl", true)[0];
 		}
 		
 		//if(serviceHlr==null){
 			serviceHlr = projectHlr.addNewMockService("hlrService");
 			serviceHlr.setPort(8090);
 			serviceHlr.setPath("/mock");
 		//}
 		hlrSyncUserInfo = (WsdlMockOperation) serviceHlr.addNewMockOperation(ifaceHlr.getOperationByName("syncCont"));
 		
 		//public static final Enum RANDOM = Enum.forString("RANDOM");
 		/*  25  public static final Enum SEQUENCE = Enum.forString("SEQUENCE");
 		
 		  27  public static final Enum SCRIPT = Enum.forString("SCRIPT");*/
 		if(method.equalsIgnoreCase("success")){
 			hlrSyncUserInfo.setDispatchStyle(MockOperationDispatchStyleConfig.SCRIPT.toString());
 			hlrSyncUserInfo.setScript("return 'Success'");
 		}else if (method.equalsIgnoreCase("fail")){
 			hlrSyncUserInfo.setDispatchStyle(MockOperationDispatchStyleConfig.SCRIPT.toString());
 			hlrSyncUserInfo.setScript("return 'Fail'");
 		}else if (method.equalsIgnoreCase("order")){
 			hlrSyncUserInfo.setDispatchStyle(MockOperationDispatchStyleConfig.SEQUENCE.toString());
 		}else if (method.equalsIgnoreCase("random")){
 			hlrSyncUserInfo.setDispatchStyle(MockOperationDispatchStyleConfig.RANDOM.toString());
 		}else if (method.equalsIgnoreCase("script")){
 			hlrSyncUserInfo.setDispatchStyle(MockOperationDispatchStyleConfig.SCRIPT.toString());
 			hlrSyncUserInfo.setScript(FileUtils.readFileToString(new File("F:/mock/hlr_syncUserInfo.groovy")));
 		}
 		WsdlMockResponse response = hlrSyncUserInfo.addNewMockResponse("Success", true);
 		response.setResponseContent(FileUtils.readFileToString(new File("F:/mock/hlrService_Success_Response.xml")));
 		
 		WsdlMockResponse response2 = hlrSyncUserInfo.addNewMockResponse("Fail", true);
 		response2.setResponseContent(FileUtils.readFileToString(new File("F:/mock/hlrService_Fail_Response.xml")));
 		serviceHlr.start();
 	   }catch(Exception e){
 		   e.printStackTrace();
 		   return false;
 	   }
 		return true;
 	}
    
    public static boolean stopMockHlrService() {
        try{		
        	serviceHlr.release();
        }catch(Exception e){
     	   return false;
        }
 		return true;
 	}
}

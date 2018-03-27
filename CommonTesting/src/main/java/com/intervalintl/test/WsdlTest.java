package com.intervalintl.test;

import com.intervalintl.services.travel.currencyconversion.ConvertCurrency;
import com.intervalintl.services.travel.currencyconversion.ConvertCurrencyRequest;
import com.intervalintl.services.travel.currencyconversion.ConvertCurrencyService;





public class WsdlTest  {

	static {
	    //for localhost testing only
	    javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
	    new javax.net.ssl.HostnameVerifier(){

	        public boolean verify(String hostname,
	                javax.net.ssl.SSLSession sslSession) {
	            if (hostname.equals("dev1-iti.ii-apps.com")) {
	                return true;
	            }
	            return false;
	        }
	    });
	}
	
	public static void main(String[] args) {  
		   
        try {  
        	System.out.println("test");
              /* String endpoint = "https://bvritid01.ii-corpnet.com:8080/travel/services/currencyConversionManager.wsdl";  
               //直接引用远程的wsdl文件  
              //以下都是套路   
               org.apache.axis.client.Service service = new org.apache.axis.client.Service();  
               Call call = (Call) service.createCall();  
               call.setTargetEndpointAddress(endpoint);  
               call.setOperationName("currencyConversion");
               //WSDL里面描述的接口名称  
               call.addParameter("userName", org.apache.axis.encoding.XMLType.XSD_DATE,  
                             javax.xml.rpc.ParameterMode.IN);//接口的参数  
               call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);//设置返回类型    
               String temp = "测试人员";  
               String result = (String)call.invoke(new Object[]{temp});  
               //给方法传递参数，并且调用方法  
               System.out.println("result is "+result);  */
        	
        	ConvertCurrencyService service = new ConvertCurrencyService();
        	ConvertCurrency con = service.getConvertCurrencyPort();
        	ConvertCurrencyRequest convertCurrencyRequest= new ConvertCurrencyRequest();
        	con.convertCurrency(convertCurrencyRequest);
        }  
        catch (Exception e) {  
               //System.err.println(e.toString());  
        	e.printStackTrace();
        }  
 }  
}

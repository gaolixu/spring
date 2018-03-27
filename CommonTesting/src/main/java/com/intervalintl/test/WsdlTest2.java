package com.intervalintl.test;

import java.net.URL;

import javax.xml.namespace.QName;

import com.intervalintl.services.travel.currencyconversion.ConvertCurrency;





public class WsdlTest2  {

	
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
	//https://bvritid01.ii-corpnet.com:8080/travel/services/currencyConversionManager.wsdl
	public static void main(String[] args) throws Exception {
	   
	URL url = new URL("https://dev1-iti.ii-apps.com:8080/travel/services/currencyConversionManager.wsdl");
        QName qname = new QName("http://services.intervalintl.com/travel/currencyConversion", "ConvertCurrencyService");

        javax.xml.ws.Service service = javax.xml.ws.Service.create(url, qname);
        ConvertCurrency hello = service.getPort(ConvertCurrency.class);
        System.out.println(hello.convertCurrency(null));
       
    }
}

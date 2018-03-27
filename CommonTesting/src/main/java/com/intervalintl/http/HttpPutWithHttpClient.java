package com.intervalintl.http;

import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;


public class HttpPutWithHttpClient {
	 public static void main(String[] args) throws Exception{
	     CloseableHttpClient httpclient = HttpClients.custom()
	      .setRetryHandler(new DefaultHttpRequestRetryHandler(0,false))
	      /*.setRedirectStrategy(new DefaultRedirectStrategy() {                
  	        
	    	  
	    	  public boolean isRedirected(HttpRequest request, HttpResponse response, HttpContext context)  {
	            boolean isRedirect=false;
	            try {
	                isRedirect = super.isRedirected(request, response, context);
	            } catch (ProtocolException e) {
	                e.printStackTrace();
	            }
	            if (!isRedirect) {
	                int responseCode = response.getStatusLine().getStatusCode();
	                if (responseCode == 301 || responseCode == 302) {
	                    return true;
	                }
	            }
	            return isRedirect;
	        }
	    	  
	    	  public URI getLocationURI(HttpRequest arg0, HttpResponse arg1,	            HttpContext arg2) throws ProtocolException {
	    	        // TODO Auto-generated method stub
	    	       // URI uri=  super.getLocationURI(arg0, arg1, arg2);
	    	        try {
						URL url = new URL(URLDecoder.decode(arg1.getFirstHeader("location").getValue(), "UTF-8"));
						 URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
						return uri;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    	        return null;
	    	    }
	    })*/
	      .build();


	     String user = "c_vgao";
	     String pwd = "interval12B";
	     CredentialsProvider credsProvider = new BasicCredentialsProvider();
	     credsProvider.setCredentials(AuthScope.ANY,
	        new NTCredentials(user, pwd, "", "II-CORPNET"));

	     // You may get 401 if you go through a load-balancer.
	     // To fix this, go directly to one the sharepoint web server or
	     // change the config. See this article :
	     // http://blog.crsw.com/2008/10/14/unauthorized-401-1-exception-calling-web-services-in-sharepoint/
	     HttpHost target = new HttpHost("portal.ilg.ad", 443, "https");
	     HttpClientContext context = HttpClientContext.create();
	     context.setCredentialsProvider(credsProvider);

	     // The authentication is NTLM.
	     // To trigger it, we send a minimal http request
	    /* HttpHead request1 = new HttpHead("/");
	     CloseableHttpResponse response1 = null;
	     try {
	       response1 = httpclient.execute(target, request1, context);
	       EntityUtils.consume(response1.getEntity());
	       System.out.println("1 : " + response1.getStatusLine().getStatusCode());
	     }
	     finally {
	       if (response1 != null ) response1.close();
	     }*/
	     
	     //create folder
	     /*CloseableHttpResponse response1 = null;
	     try {
	    	 BasicHttpRequest httpPost = new BasicHttpRequest("MKCOL", "/sites/repository/pmo/IProj/cc_tokenization/Shared%20Documents/PROD%20Reconciliation%20Reports/iServices/2018-02");
	    	    HttpUriRequest httpUriRequest = new RequestWrapper(httpPost); 
	       response1 = httpclient.execute(target, httpUriRequest, context);
	       EntityUtils.consume(response1.getEntity());
	       System.out.println("1 : " + response1.getStatusLine().getStatusCode());
	       System.out.println("1 : " + response1.getStatusLine().getReasonPhrase());
	     }
	     finally {
	       if (response1 != null ) response1.close();
	     }*/
	     
	     
	     //check if filder exists
	     CloseableHttpResponse response1 = null;
	     try {
	    	// HttpGet httpGet = new HttpGet("/sites/repository/pmo/IProj/cc_tokenization/Shared%20Documents/PROD%20Reconciliation%20Reports/iServices/2018-02");
	    	    HttpHead httpGet = new HttpHead("/sites/repository/pmo/IProj/cc_tokenization/Shared%20Documents/PROD%20Reconciliation%20Reports/iServices/2018-02");          
	    	 
	       response1 = httpclient.execute(target, httpGet, context);
	       EntityUtils.consume(response1.getEntity());
	       System.out.println("1 : " + response1.getStatusLine().getStatusCode());
	       System.out.println("1 : " + response1.getStatusLine().getReasonPhrase());
	       //1 : 404	       1 : NOT FOUND
	     }
	     finally {
	       if (response1 != null ) response1.close();
	     }
	     
	     

	   /*  // The real request, reuse authentication
	     File file = new File("D://ml_reconciliation_report-20170614.csv");
	     //String file = "/30500C/PubDoc/TEST/jira.log";
	     HttpPut request2 = new HttpPut("/sites/repository/pmo/IProj/cc_tokenization/Shared%20Documents/PROD%20Reconciliation%20Reports/iServices/2018-02/ml_reconciliation_report-20170614.csv");  // target
	     request2.setEntity(new FileEntity(file));// source
	     CloseableHttpResponse response2 = null;
	     try {
	       response2 = httpclient.execute(target, request2, context);
	       EntityUtils.consume(response2.getEntity());
	       int rc = response2.getStatusLine().getStatusCode();
	       String reason = response2.getStatusLine().getReasonPhrase();
	       // The possible outcomes :
	       //    201 Created
	       //        The request has been fulfilled and resulted in a new resource being created
	       //    200 OK
	       //        Standard response for successful HTTP requests.
	       //    others
	       //        we have a problem
	       if (rc == HttpStatus.SC_CREATED) {
	         System.out.println(file + " is copied (new file created)");
	       }
	       else if (rc == HttpStatus.SC_OK) {
	         System.out.println(file + " is copied (original overwritten)");
	       }
	       else {
	         throw new Exception("Problem while copying " + file  + "  reason " + reason + "  httpcode : " + rc);
	       }
	     }
	     finally {
	       if (response2 != null) response2.close();
	     }*/
	     return;
	   }
	 
	
}

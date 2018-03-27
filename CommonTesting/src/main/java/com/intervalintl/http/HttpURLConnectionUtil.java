package com.intervalintl.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.codec.binary.Base64;

public class HttpURLConnectionUtil {

	
	public static final String POST = "POST";
	
	public static final String GET = "GET";
	
	public static final String JSON_CONTENT_TYPE = "application/json";

		
	
	//keytool -import -trustcacerts -file "D:/esb-.ii-apps.com.crt" -alias esb -keystore "C:/Program Files/Java/jdk1.6.0_30/jre/lib/security/cacerts"
	public static void main2(String[] args) throws IOException {
		  /* String authorization = "AqergsY8q56XUzH7v1v2rbgI-tg_w3DSTF5gFs8E5upTpOignW-xmIlWXoBNe-k1ZO5pLKr3dfudSgwD0zYTYg";
			Map<String,String> requestProperties = new HashMap<String,String>();
			//requestProperties.put(RequestPropertyEnum.CONTENT_TYPE.getCode(), HttpURLConnectionUtil.JSON_CONTENT_TYPE);
//			requestProperties.put(RequestPropertyEnum.APPLICATION_ID.getCode(), applicationId);
			requestProperties.put(RequestPropertyEnum.AUTHORIZATION.getCode(), authorization);		
		   System.out.println(HttpURLConnectionUtil.readContentFromConnection("https://dev2-msb.ii-apps.com:9443/agent/cobrands/current", "GET", null, requestProperties));
	*/
		
	    BufferedReader br = new BufferedReader(new FileReader(new File("d://offerRequest.xml")));
	    String line;
	    StringBuilder sb = new StringBuilder();

	    while((line=br.readLine())!= null){
	        sb.append(line.trim());
	    }
	    br.close();
	    //URL url = new URL("https://dev2-mag.ii-apps.com:9443/proxy/insurance/offers");
	    //String response = HttpURLConnectionUtil.invoke("https://tv1var.merchantlink-lab.com:8184/TV2G", sb.toString(),"POST", requestProperties);
	    try {  
            URL url = new URL("https://dev2-mag.ii-apps.com:9443/proxy/insurance/offers");  
            URLConnection con = url.openConnection();  
            con.setDoOutput(true);  
            /// con.setRequestProperty("Pragma:", "no-cache");  
            // con.setRequestProperty("Cache-Control", "no-cache");  
            con.setRequestProperty("Content-Type", "text/xml");  
            con.setRequestProperty("SOAPAction", "http://tempuri.org/I_ProductPriceServiceExt/GetProductPriceOffer");  
            //con.setRequestProperty("Transfer-Encoding","chunked");
           // ((HttpsURLConnection)con).setChunkedStreamingMode(100);
  
            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());      
            String xmlInfo = sb.toString();  
            
            out.write(new String(xmlInfo.getBytes("UTF-8")));  
            out.flush();  
            out.close();  
            BufferedReader br2 = new BufferedReader(new InputStreamReader(con  
                    .getInputStream()));  
            String line2 = "";  
            for (line2 = br2.readLine(); line2 != null; line2 = br2.readLine()) {  
                System.out.println(line2);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
		
		
	
	
	}
	
	public static void main(String[] args) throws Exception {
		/*final URL url = new URL("https://dev4-iid.ii-apps.com:443/id/services/inventoryService");
		final HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		
		conn.connect();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    String inputLine;

	    while ((inputLine = in.readLine()) != null) {
	        System.out.println(inputLine);
	    }
	    in.close();*/
	    //openssl s_client -connect qa2-intmag.ii-apps.com:443 -showcerts
	   // openssl s_client -connect staging-intmsb.ii-apps.com:443 -showcerts
	    //openssl s_client -connect qa2-intmsb.ii-apps.com:443 -showcerts  http://dev4-iti.ii-apps.com:8080/travel/services/hotelExchange
	   // -Djavax.net.debug=all
	    // -Dhttps.protocols=TLSv1,TLSv1.1,TLSv1.2
	    //openssl s_client -connect dev4-iid.ii-apps.com:8080 -showcerts -servername dev4-iid.ii-apps.com
		
		
		FileOutputStream fos=null; 
		HttpURLConnection conn=null; 
		try { 
			
			
			/*AVS Gateway Response Code	
			CSC Gateway Response Code	
			3-D Secure Authentication -  Authentication Status  	
			3-D Secure Authentication -  ECI        	
			Reference Retrieval Number (RRN)   	
			Payer Order Reference         	
			User ID  */
		
		URL url=new URL("https://cnp.merchantlink.com/history/version/1/merchant/TESTLEISGRP3/transaction?columns=merchant,order.id,order.reference,transaction.id,transaction.reference,"
				+ "transaction.acquirer.id,timeOfRecord,sourceOfFunds.provided.card.fundingMethod,transaction.type,transaction.amount,transaction.currency,"
				+ "response.gatewayCode,sourceOfFunds.provided.card.scheme,sourceOfFunds.provided.card.number,sourceOfFunds.provided.card.nameOnCard,"
				+ "transaction.authorizationCode,response.acquirerCode,transaction.acquirer.batch,transaction.source,risk.response.gatewayCode,risk.response.review.decision,"
				+ "response.cardholderVerification.avs.gatewayCode,response.cardSecurityCode.gatewayCode,3DSecure.authenticationStatus,3DSecure.acsEci,transaction.receipt,order.customerReference,userId"
				+ "&columnHeaders=Merchant%20ID,Order%20ID,Order%20Reference,Transaction%20ID,Transaction%20Reference,"
				+ "Acquirer%20ID,Transaction%20Date,Card%20-%20Funding%20Method,Transaction%20Type,Transaction%20Amount%20(amount%20only),Transaction%20Amount%20(currency%20only),"
				+ "Transaction%20Gateway%20Response%20Code,Payment%20Method,Account%20Identifier,Account%20Holder,"
				+ "Authorization%20Code,Acquirer%20Response%20Code,Acquirer%20Batch%20Number,Transaction%20Source,Risk%20Assessment%20Result,"
				+ "Risk%20Review%20-%20Decision%20Status,AVS%20Gateway%20Response%20Code,CSC%20Gateway%20Response%20Code,3-D%20Secure%20Authentication%20-%20Authentication%20Status,3-D%20Secure%20Authentication%20-%20ECI,"
				+ "Reference%20Retrieval%20Number%20(RRN),Payer%20Order%20Reference,User%20ID&timeOfRecord.start=2017-06-13T00:00:00Z&timeOfRecord.end=2017-06-14T00:00:00Z"); 
		
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("ii-internetproxy.ii-corpnet.com", Integer.valueOf("3128")));				
		        conn=(HttpURLConnection)url.openConnection(proxy);
		        conn.setReadTimeout(30000);
		        Map<String,String> requestProperties= getRequestProperties();
		        if (requestProperties != null && !requestProperties.isEmpty()) {
					for (Entry<String, String> entry : requestProperties.entrySet()) {
						conn.setRequestProperty(entry.getKey(), entry.getValue());
					}
				}

		        if(conn.getResponseCode()==HttpURLConnection.HTTP_OK){

		            InputStream is=conn.getInputStream();

		            fos=new FileOutputStream(new File("D:\\ml_reconciliation_report-20170614.csv"));

		            byte[] b=new byte[1024];
		            int len=0;
		            while((len=is.read(b))!=-1){   
		                fos.write(b, 0, len);
		            }
		            fos.flush();
		            System.out.println("Downloaded");
		        }else{
		        	System.out.println(conn.getResponseCode());
		        }


		    } catch (MalformedURLException e) {
		        e.printStackTrace();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }finally{
		        if(fos!=null){
		            try {
		                fos.close();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
		    }
		}
	
	private static Map<String,String> getRequestProperties() {
		Map<String,String> requestProperties = new HashMap<String,String>();
		//requestProperties.put(RequestPropertyEnum.CONTENT_LENGTH.getCode(), Integer.toString(reqeust.getBytes().length));
		requestProperties.put("Authorization", getAuthentication());
		return requestProperties;
	}
	
	private static String getAuthentication() {
		
		byte[] encodedBytes = Base64.encodeBase64(("merchant.default:"+"3a92869249d9e418dde93c830aaa7b4a").getBytes());
		return "Basic " + new String(encodedBytes);
	}
}

package com.intervalintl.http;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public class SharePoint {


     //https://portal.ilg.ad/sites/repository/pmo/IProj/cc_tokenization/Shared%20Documents/PROD%20Reconciliation%20Reports/iServices
	//keytool -import -trustcacerts -file D://ilg.ad.crt -alias ilg.ad -keystore "C:/Program Files/Java/jdk1.7.0_71/jre/lib/security/cacerts"
//https://portal.ilg.ad/sites/repository/pmo/IProj/cc_tokenization/Shared%20Documents/Forms/AllItems.aspx?RootFolder=%2fsites%2frepository%2fpmo%2fIProj%2fcc%5ftokenization%2fShared%20Documents%2fPROD%20Reconciliation%20Reports%2fiServices&FolderCTID=0x0120005E3983E68CD1FF46AB688261E676DBCF
	 public static void main(String[] args) throws Exception  
	    {
		 
		 DefaultHttpClient httpclient = new DefaultHttpClient();
	        NTCredentials creds = new NTCredentials("c_vgao", "interval12B", "BW7FRBP10)", "II-CORPNET.COM");
	        httpclient.getCredentialsProvider().setCredentials(AuthScope.ANY, creds);
	        HttpHost target = new HttpHost("portal.ilg.ad", 443, "https");
	        // Make sure the same context is used to execute logically related requests
	        HttpContext localContext = new BasicHttpContext();
	        // Execute a cheap method first. This will trigger NTLM authentication
	                    HttpGet httpget = new HttpGet("/sites/repository/pmo/IProj/cc_tokenization/Shared%20Documents/PROD%20Reconciliation%20Reports/iServices/2017-12/interval_reconciliation_report-20171201.csv");
	                   // HttpGet httpget = new HttpGet("/sites/repository/pmo/IProj/cc_tokenization/Shared%20Documents/PROD%20Reconciliation%20Reports/iServices");
	                    
	        //httpget.setHeader("accept", "application/json"); 
	        HttpResponse response1 = null;
	        /*try {
	            response1 = httpclient.execute(target, httpget, localContext);
	            //System.out.print(response1.toString());
	        } catch (ClientProtocolException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        HttpEntity entity1 = response1.getEntity();*/
	        
	        
	        //String soapRequestData= "testtest";
	        HttpPost   httpPost = new HttpPost("/sites/repository/pmo/IProj/cc_tokenization/Shared%20Documents/PROD%20Reconciliation%20Reports/iServices/2018-01");
	       // PostMethod postMethod = new PostMethod("/sites/repository/pmo/IProj/cc_tokenization/Shared%20Documents/PROD%20Reconciliation%20Reports/iServices/2018-01");

	 	   /*byte[] b = soapRequestData.getBytes("utf-8");
	 	   InputStream is = new ByteArrayInputStream(b,0,b.length);
	 	   RequestEntity re = new InputStreamRequestEntity(is,b.length,"application/soap+xml; charset=utf-8");
	 	   postMethod.setRequestEntity(re); 
	 	   postMethod.setDoAuthentication(true);*/
	        File file = new File("D://ml_reconciliation_report-20171207.csv");
	        FileBody bin = new FileBody(file);
	        
	          
	                     HttpEntity reqEntity =new FileEntity(file);
	                          
	       
	                     httpPost.setEntity(reqEntity);
	       
	 	  try {
	            response1 = httpclient.execute(target, httpPost, localContext);
	            //System.out.print(response1.toString());
	        } catch (ClientProtocolException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        
	        try {
	                    
	             // delFile("c:/自定义列表.xml");
	             
	              //saveFile("D:/自定义列表.xml",EntityUtils.toString(entity1),false);
	                                                   
	           
	        	
	        	
	        	/*SAXReader reader = new SAXReader();
	            
	             try
	                {	                
	                                 
	                Document document = reader.read(new File("c:/自定义列表.xml"));
	                                 
	                 Element rootElm = document.getRootElement();
	                 
	                 // 枚举根节点下所有子节点       
	                 for (Iterator ie = rootElm.elementIterator(); ie.hasNext();) 
	                 {        
	                     Element element = (Element) ie.next();      
	               
	                     if(element.getName()=="entry")
	                     {
	                                                 for (Iterator ic = element.elementIterator(); ic.hasNext();) 
	                         {      
	                             Element Celement = (Element) ic.next(); 
	                             
	                             if(Celement.getName()=="content")
	                              {
	                             
	                                 for (Iterator ip = Celement.elementIterator(); ip.hasNext();) 
	                                 {      
	                                     Element Pelement = (Element) ip.next(); 
	                                     
	                                     for (Iterator ix = Pelement.elementIterator(); ix.hasNext();) 
	                                     {      
	                                         Element Xelement = (Element) ix.next(); 
	                                         
	                                         if(Xelement.getName()=="人员名称" || Xelement.getName()=="应发工资")
	                                         {
	                                             System.out.println(Xelement.getName()+":"+Xelement.getData());
	                                         }
	                                     }
	                                     //System.out.println(Pelement.getName());
	                                 }
	                                 //System.out.println(Celement.getName());
	                              }
	                         }
	                                            }
	                     
	                 }
	                 }catch (Exception e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }*/
	            
	        
	        	
	        	
	        	
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }

	        
	    }

	    
	      
	     public static void saveFile(String file, String data, boolean append) throws IOException {
	          BufferedWriter bw = null;
	          OutputStreamWriter osw = null;
	            
	          File f = new File(file);
	          FileOutputStream fos = new FileOutputStream(f, append);
	          try {
	             // write UTF8 BOM mark if file is empty
	             if (f.length() < 1) {
	                final byte[] bom = new byte[] { (byte)0xEF, (byte)0xBB, (byte)0xBF };
	                fos.write(bom);
	             }

	             osw = new OutputStreamWriter(fos, "UTF-8");
	             bw = new BufferedWriter(osw);
	             if (data != null) bw.write(data);
	          } catch (IOException ex) {
	             throw ex;
	          } finally {
	             try { bw.close(); fos.close(); } catch (Exception ex) { }
	          }
	       }

	    /**  
	       * 删除文件  
	       * @param filePathAndName String 文件路径及名称 如c:/file.xml  
	       * @param fileContent String  
	       * @return boolean  
	       */  
	      public static void delFile(String filePathAndName) {   
	        try {   
	          String filePath = filePathAndName;   
	         filePath = filePath.toString();   
	         java.io.File myDelFile = new java.io.File(filePath);   
	          myDelFile.delete();   
	      
	        }   
	        catch (Exception e) {   
	         System.out.println("删除文件操作出错");   
	          e.printStackTrace();   
	      
	        }   
	      
	      }   
	      
	      
	
}

package com.intervalintl.network;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebSpider {
	public static void main(String[] args) {  
        URL url = null;  
        URLConnection urlconn = null;  
        BufferedReader br = null;  
        PrintWriter pw = null;  
        String regex = "http://[\\w+\\.?/?]+\\.[A-Za-z]+";  
        Pattern p = Pattern.compile(regex);  
        try {  
            //url = new URL("https://www.google.com/search?q=java&oq=java");  
        	url = new URL("http://www.baidu.com/s?word=java");  
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("ii-internetproxy.ii-corpnet.com", Integer.valueOf("3128")));				
            urlconn = (HttpURLConnection) url.openConnection(proxy);            
            
            //urlconn = url.openConnection();  
            //pw = new PrintWriter(new FileWriter("D:/url.txt"), true);
            br = new BufferedReader(new InputStreamReader(  
                    urlconn.getInputStream()));  
            String buf = null;  
            while ((buf = br.readLine()) != null) {  
                Matcher buf_m = p.matcher(buf);  
                while (buf_m.find()) {  
                    //pw.println(buf_m.group());  
                	System.out.println(buf_m.group());
                }  
            }  
            System.out.println("Success !");  
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                br.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
            //pw.close();  
        }  
    }  
}

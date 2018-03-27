package com.intervalintl.network;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class CrawlerMainMT {
	public static void main(String[] args) throws Exception {

        /*if(args.length < 1) {
            System.err.println("Provide a URL as argument to the CrawlerMainMT class.");
            return;
        }*/

        //String url = args[0];
        String url = "http://tutorials.jenkov.com";
       /* CrawlerMT crawler  = new CrawlerMT(new SameWebsiteOnlyFilter(url));
        crawler.addUrl(url);
        crawler.crawl();*/
        
        
        setProxy();
        
        	/*URLConnection urlConnection=null;;
			try {
				urlConnection = new URL(url).openConnection();
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}          
            
            try (InputStream input = urlConnection.getInputStream()) {
                  Document doc = Jsoup.parse(input, "UTF-8", url);
                  System.out.println(doc);
            }catch (Exception e){           	
            	
            }*/
            
            
            URL url2 = new URL("http://tutorials.jenkov.com");
            HttpURLConnection connection = (HttpURLConnection)url2.openConnection();
            //默认就是Get，可以采用post，大小写都行，因为源码里都toUpperCase了。
            connection.setRequestMethod("GET");
            //是否允许缓存，默认true。
            connection.setUseCaches(Boolean.FALSE);
            //是否开启输出输入，如果是post使用true。默认是false
            //connection.setDoOutput(Boolean.TRUE);
            //connection.setDoInput(Boolean.TRUE);
            //设置请求头信息
            connection.addRequestProperty("Connection", "close");
            connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0");
            //connection.addRequestProperty("Accept-Encoding", "gzip, deflate");
            //设置连接主机超时（单位：毫秒）  
            connection.setConnectTimeout(8000);  
             //设置从主机读取数据超时（单位：毫秒）  
            connection.setReadTimeout(8000);    
            //设置Cookie
            connection.addRequestProperty("Cookie","你的Cookies" );
            //开始请求
            System.out.println("Content type: "+connection.getContentType());
            InputStream in=  connection.getInputStream();
           ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int next = in.read();
            while (next != -1) {
            	bos.write(next);
            	next = in.read();
            }
            bos.flush();
            byte[] result = bos.toByteArray();
            Document doc = Jsoup.parse(new URL(url).openStream(), null, "http://tutorials.jenkov.com");
            
          //  BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
            System.out.println(doc);
            
            
           Document doc2 = Jsoup.connect(url)
            	    .header("Accept-Encoding", "gzip, deflate")
            	    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0")
            	    .maxBodySize(0)
            	    .timeout(600000)
            	    .get();
            
            System.out.println(doc2);
}
	
	
	 public static void setProxy(){
			// System.setProperty("http.maxRedirects", "50");
	         System.getProperties().setProperty("proxySet", "true");
	         // 如果不设置，只要代理IP和代理端口正确,此项不设置也可以
	         String ip = "ii-internetproxy.ii-corpnet.com";
	         System.getProperties().setProperty("http.proxyHost", ip);
	         System.getProperties().setProperty("http.proxyPort", "3128");
	         
	         //for https:
	         System.getProperties().setProperty("https.proxyHost", ip);
	         System.getProperties().setProperty("https.proxyPort", "3128");
		}
}

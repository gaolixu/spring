package com.intervalintl.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.BrokenBarrierException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlJobMT implements Runnable {

    protected CrawlerMT crawler    = null;
    protected String  urlToCrawl = null;
    
    public CrawlJobMT(String urlToCrawl, CrawlerMT crawler) {
        this.urlToCrawl = urlToCrawl;
        this.crawler    = crawler;
    }
    @Override
    public void run(){
        try{
            crawl();
        }catch(Exception ex){
            
        }
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
    public void crawl() throws IOException{
      setProxy();
        URL url = new URL(this.urlToCrawl);

        //URLConnection urlConnection = null;
        try {
           // urlConnection = url.openConnection();

            Connection conn = Jsoup.connect(this.urlToCrawl).ignoreContentType(true);; 
            Document  doc = conn.timeout(100000).userAgent("Mozilla").get(); 
            
           // try (InputStream input = urlConnection.getInputStream()) {

               // Document doc = Jsoup.parse(input, "UTF-8", "");
                Elements elements = doc.select("a");

                String baseUrl = url.toExternalForm();
                for(Element element : elements){
                    String linkUrl       = element.attr("href");
                    String normalizedUrl = UrlNormalizer.normalize(linkUrl, baseUrl);
                    crawler.linksQueue.put(normalizedUrl);
                    
                    System.out.println(" - "+normalizedUrl);
                    
                }
                if(crawler.barrier.getNumberWaiting()==1){
                    crawler.barrier.await();
                    
                }

            } catch (IOException e) {
                throw new RuntimeException("Error connecting to URL", e);
            } catch (InterruptedException ex) {
                Logger.getLogger(CrawlJobMT.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BrokenBarrierException ex) {
                Logger.getLogger(CrawlJobMT.class.getName()).log(Level.SEVERE, null, ex);
            } 
    }

}
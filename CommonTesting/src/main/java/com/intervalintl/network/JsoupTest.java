package com.intervalintl.network;

import java.util.List;

public class JsoupTest {
	@org.junit.Test  
    public void getDatasByClass()  
    {  
        Rule rule = new Rule(  
                "http://www1.sxcredit.gov.cn/public/infocomquery.do?method=publicIndexQuery",  
        new String[] { "query.enterprisename","query.registationnumber" }, new String[] { "兴网","" },  
                "cont_right", Rule.CLASS, Rule.POST);  
        List<LinkTypeData> extracts = ExtractService.extract(rule);  
        printf(extracts);  
    }  
  
    @org.junit.Test  
    public void getDatasByCssQuery()  
    {  
        Rule rule = new Rule("http://www.11315.com/search",  
                new String[] { "name" }, new String[] { "兴网" },  
                "div.g-mn div.con-model", Rule.SELECTION, Rule.GET);  
        List<LinkTypeData> extracts = ExtractService.extract(rule);  
        printf(extracts);  
    }  
    
    
    @org.junit.Test  
    public void getDatasByCssQueryUserBaidu()  
    {  
        Rule rule = new Rule("http://www.baidu.com/s",  
                new String[] { "word" }, new String[] { "test" },  
                null, -1, Rule.GET);  
        List<LinkTypeData> extracts = ExtractService.extract(rule);  
        printf(extracts);  
    }  
  
    public static void printf(List<LinkTypeData> datas)  
    {  
        for (LinkTypeData data : datas)  
        {  
            System.out.println(data.getLinkText());  
            System.out.println(data.getLinkHref());  
            System.out.println("***********************************");  
        }  
  
    }  
    
    public static void main(String[] args) {
    	/* Rule rule = new Rule("http://www.baidu.com/s?word=java",  
               null, null,  
                 null, -1, Rule.GET);  
         List<LinkTypeData> extracts = ExtractService.extract(rule);  
         printf(extracts);*/  
         
         Rule rule = new Rule("https://www.google.com:443/search",  
        		 new String[] { "hl","q" }, new String[] { "en","hello" },  
                   null, -1, Rule.GET);  
           List<LinkTypeData> extracts = ExtractService.extract(rule);  
           printf(extracts);  
	}
}

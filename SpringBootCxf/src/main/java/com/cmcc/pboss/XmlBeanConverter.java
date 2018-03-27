package com.cmcc.pboss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.stream.StreamSource;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.xml.transform.StringResult;

public class XmlBeanConverter {
	
//	private static final Logger LOGGER = Logger.getLogger(XmlBeanConverter.class.getName());
	
	public static String convertToXml(Object obj){
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller(); 
		Map<String,Object> properties = new HashMap<String, Object>();
	    properties.put(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	    marshaller.setClassesToBeBound(obj.getClass());
	    marshaller.setMarshallerProperties(properties);

	    StringResult stringResult = new StringResult();
	    marshaller.marshal(obj, stringResult);
	    
	    return stringResult.toString();
    }  
	
	@SuppressWarnings("unchecked")
	public static <T> T convertToJavaBean(String xml, Class<T> c) {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller(); 
		Map<String,Object> properties = new HashMap<String, Object>();
	    properties.put(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	    marshaller.setClassesToBeBound(c);
	    marshaller.setMarshallerProperties(properties);
	    
		return (T)marshaller.unmarshal(new StreamSource(new StringReader(xml)));
    }
	

	public static void main(String[] args) throws Exception {}
	
	private static String readXml() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("d://request.xml")));
		String line;
		StringBuilder sb = new StringBuilder();

		while ((line = br.readLine()) != null) {
			sb.append(line.trim());
		}
		br.close();
		return sb.toString();
	}
}

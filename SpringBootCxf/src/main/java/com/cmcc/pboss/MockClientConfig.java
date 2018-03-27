package com.cmcc.pboss;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.SpringServletContainerInitializer;

import com.symbio.services.mock.allnetservice.AllnetServicePort;
import com.symbio.services.mock.hlrservice.HlrServicePort;


//@EnableWs
@Configuration
@PropertySource(value = { "classpath:pboss.properties" },ignoreResourceNotFound=true)
public class MockClientConfig extends SpringServletContainerInitializer {
	 @Value("${client.allnet.address}")
	  private String allnetAddress;
	 
	 @Value("${client.hlr.address}")
	  private String hlrAddress;

	  @Bean(name = "allnetServiceProxy")
	  public AllnetServicePort allnetServiceProxy() {
	    JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
	    jaxWsProxyFactoryBean.setServiceClass(AllnetServicePort.class);
	    jaxWsProxyFactoryBean.setAddress(allnetAddress);

	    return (AllnetServicePort) jaxWsProxyFactoryBean.create();
	  }
	  
	  @Bean(name = "hlrServiceProxy")
	  public HlrServicePort hlrServiceProxy() {
	    JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
	    jaxWsProxyFactoryBean.setServiceClass(HlrServicePort.class);
	    jaxWsProxyFactoryBean.setAddress(hlrAddress);

	    return (HlrServicePort) jaxWsProxyFactoryBean.create();
	  }
   
}

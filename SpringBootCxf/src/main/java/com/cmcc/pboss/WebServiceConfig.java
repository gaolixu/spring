package com.cmcc.pboss;

import java.util.Arrays;

import org.apache.catalina.Context;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.SpringServletContainerInitializer;


//@EnableWs
@Configuration
@ImportResource({
	"classpath:spring/cxf-ws.xml"
})
public class WebServiceConfig extends SpringServletContainerInitializer {

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new CXFServlet(), "/services/*");
    }
    
    
    /*@Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
        factory.setTomcatContextCustomizers(Arrays.asList(new CustomCustomizer()));
        return factory;
    }

    static class CustomCustomizer implements TomcatContextCustomizer {
        @Override
        public void customize(Context context) {
            context.setUseHttpOnly(true);
        }
    }
    */
    
    
   /* @Bean
    public ServletRegistrationBean messageDispatcherServlet(MessageDispatcherServlet servlet) {
        return new ServletRegistrationBean(servlet, "/proxy/*");
    }

    @Bean
    public MessageDispatcherServlet servlet() {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setTransformWsdlLocations(true);
        return servlet;
    }
    
    @Bean
    public MessageEndpointAdapter adapter() {
        return new MessageEndpointAdapter();
    }
*/
}

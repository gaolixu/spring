/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.codecentric.batch.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * This is the main configuration class for the application.
 *
 * @author Thomas Bosch
 */
@Configuration
@Import({ ServletConfiguration.class, WebappConfiguration.class })
/*@PropertySource(value = { "classpath:batch-hsql.properties",
"classpath:your-properties.properties" },ignoreResourceNotFound=true)*/
//@EnableAutoConfiguration
public class MainV1Configuration {

	    @Bean
	    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() { 
	        
	        Resource[] resources = {
	            new ClassPathResource("batch-hsql.properties"),
	            new ClassPathResource("your-properties.properties")
	        };
	        
	        PropertySourcesPlaceholderConfigurer bean = new PropertySourcesPlaceholderConfigurer();
	        bean.setLocations(resources);
	        bean.setOrder(0);
	         
	        return bean; 
	    } 
}

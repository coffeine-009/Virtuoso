package com.coffeine.virtuoso.configuration;

import java.net.MalformedURLException;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.UrlResource;

@EnableAspectJAutoProxy
@Configuration
@ComponentScan(basePackages = { "ua.upc.mobicard" })
public class RootConfig {

	@Bean
	public static PropertyPlaceholderConfigurer properties() throws MalformedURLException {
		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
//		ppc.setIgnoreUnresolvablePlaceholders(true);
		ppc.setIgnoreResourceNotFound(true);
		ppc.setSystemPropertiesModeName("SYSTEM_PROPERTIES_MODE_OVERRIDE");
		//String mcHome = System.getProperty("MOBICARD_HOME","");
//		ppc.setLocations(new org.springframework.core.io.Resource[] {
//				new ClassPathResource("mobicard-default.properties")
//				, new UrlResource("file://"+mcHome+"/conf/mobicard.properties")
//				, new ClassPathResource("persistence-default.properties")
//				, new UrlResource("file://"+mcHome+"/conf/persistence.properties")
//				, new ClassPathResource("security-default.properties")
//				, new UrlResource("file://"+mcHome+"/conf/security.properties")
//		});
		ppc.setValueSeparator(":");
		return ppc;
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("i18n/messages");
		messageSource.setUseCodeAsDefaultMessage(true);
		return messageSource;
	}


}
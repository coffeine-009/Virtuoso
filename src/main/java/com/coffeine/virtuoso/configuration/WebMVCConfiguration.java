///	***	Configuration :: WebMVCConfiguration	***	***	***	***	***	***	***	///

	/**	***	***	***	***	***	***	***	***	***	***	***	***	***	***	***	***	*
	 *																	*
	 * @copyright 2014 (c), by Coffeine
	 *
	 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
	 *
	 * @date 2014-03-25 15:36:00 :: ....-..-.. ..:..:..
	 *
	 * @address /Ukraine/Ivano-Frankivsk/Tychyny/7a
	 *																	*
	*///***	***	***	***	***	***	***	***	***	***	***	***	***	***	***	***	*

///	***	Code	***	***	***	***	***	***	***	***	***	***	***	***	***	***	***	///
package com.coffeine.virtuoso.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 *
 */
@Configuration
@ComponentScan(
	basePackages = { "com.coffeine.virtuoso.module.user.controller" },
	includeFilters = {
		@Filter( value = Controller.class )
	}
)
public class WebMVCConfiguration extends WebMvcConfigurationSupport {

	///	***	Properties	***	///
    private static final String MESSAGE_SOURCE = "/WEB-INF/i18n/messages";

	///	***	Methods		***	///
	/**
	 *
	 * @return RequestMappingHandlerMapping
	 */
    @Override
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        RequestMappingHandlerMapping requestMappingHandler = super.requestMappingHandlerMapping();
			requestMappingHandler.setUseSuffixPatternMatch( false );
			requestMappingHandler.setUseTrailingSlashMatch( false );

        return requestMappingHandler;
    }

	/**
	 *
	 * @return MessageSource
	 */
    @Bean( name = "messageSource" )
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
			messageSource.setBasename( MESSAGE_SOURCE );
			messageSource.setCacheSeconds( 5 );

        return messageSource;
    }

	/**
	 *
	 * @return Validator
	 */
    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
			validator.setValidationMessageSource( this.messageSource() );

        return validator;
    }

	/**
	 *
	 * @param configurer
	 */
    @Override
    public void configureDefaultServletHandling(
		DefaultServletHandlerConfigurer configurer
	) {
        configurer.enable();
    }
}

package com.thecoffeine.virtuoso.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * Resource server configuration.
 *
 * @version 1.0
 */
@Profile( "default" )
@Configuration
@EnableResourceServer
public class SecurityConfig {//} extends ResourceServerConfigurerAdapter {

//    @Override
//    public void configure( HttpSecurity http ) throws Exception {
//        http.csrf().disable();
//        http.httpBasic().disable();
//
//        http.requestMatchers().antMatchers( "/music/**" )
//            .and()
//            .authorizeRequests()
//            .antMatchers( "/music/songs**" ).permitAll()
//            .anyRequest().authenticated();
//
//        http.sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS);
//    }
}

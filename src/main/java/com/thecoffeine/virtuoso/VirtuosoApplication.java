package com.thecoffeine.virtuoso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * Virtuoso application.
 *
 * @version 1.0
 */
@SpringBootApplication
@EnableGlobalMethodSecurity( prePostEnabled = true )
@EnableDiscoveryClient
public class VirtuosoApplication {

    /**
     * Entry point.
     *
     * @param args    App's args.
     */
    public static void main( String [] args ) {
        SpringApplication.run( VirtuosoApplication.class, args );
    }
}

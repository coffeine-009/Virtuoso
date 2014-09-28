/**
 * @copyright 2014 (c), by Vitaliy Tsutsman
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 2014-09-05 19:00:00 :: 2014-09-05 19:18:40
 *
 * @address /Ukraine/Ivano-Frankivsk/Petranka
 */
package com.coffeine.virtuoso.module.controller;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


/**
 * Abstract base test for controllers
 *
 * @version 1.0
 */
@ActiveProfiles( "test" )
@ContextConfiguration(
    locations = {
        "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml",
        "file:src/main/webapp/WEB-INF/spring/spring-context-test.xml"
    }
)
@RunWith( SpringJUnit4ClassRunner.class )
@WebAppConfiguration
public abstract class AbstractControllerTest
    extends
        AbstractTransactionalJUnit4SpringContextTests
{
    /// *** Properties  *** ///
    /**
     * MVC mock use for test with out real data base
     */
    protected MockMvc mockMvc;

    /**
     * Application context
     */
    @Autowired
    private WebApplicationContext webApplicationContext;

    /**
     * Security
     */
    @Autowired
    private FilterChainProxy springSecurityFilterChainProxy;


    /// *** Methods     *** ///
    /**
     * Prepare environment to run tests
     */
    public void tearUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(
            this.webApplicationContext
        )
            .addFilter( this.springSecurityFilterChainProxy )
            .build();
    }
}

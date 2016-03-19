/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 3/13/16 5:07 PM
 */

package com.coffeine.virtuoso.module;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.RestDocumentation;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

/**
 * Base class for functional tests.
 *
 * @version 1.0
 */
@ActiveProfiles( "tests" )
@ContextConfiguration(
    locations = {
        "file:src/main/webapp/WEB-INF/spring/mvc-dispatcher-servlet.xml",
        "file:src/main/webapp/WEB-INF/spring/tests/mail.xml",
        "file:src/main/webapp/WEB-INF/spring/notification.xml",
        "file:src/main/webapp/WEB-INF/spring/tests/spring-context.xml",
        "file:src/main/webapp/WEB-INF/spring/development/security.xml",//FIXME
        "file:src/main/webapp/WEB-INF/spring/development/configuration.xml"//FIXME
    }
)
@RunWith( SpringJUnit4ClassRunner.class )
@WebAppConfiguration
public abstract class AbstractTest
    extends
        AbstractTransactionalJUnit4SpringContextTests
{
    /**
     * Rest documentation generator.
     */
    @Rule
    public final RestDocumentation restDocumentation = new RestDocumentation( "target/generated-snippets" );


    /// *** Properties  *** ///
    /**
     * MVC mock use for test with out real data base.
     */
    protected MockMvc mockMvc;

    /**
     * Application context.
     */
    @Autowired
    private WebApplicationContext webApplicationContext;

    /**
     * Security.
     */
    @Autowired
    private FilterChainProxy springSecurityFilterChainProxy;


    /// *** Methods     *** ///
    /**
     * Prepare environment to run tests.
     */
    public void tearUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(
            this.webApplicationContext
        )
            .addFilter( this.springSecurityFilterChainProxy )
            .apply( documentationConfiguration( this.restDocumentation ) )
            .build();
    }

    /**
     * Clean environment.
     */
    public void tearDown() {

    }
}

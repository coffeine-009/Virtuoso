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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

/**
 * Abstract base test for controllers
 *
 * @version 1.0
 */
@ActiveProfiles( "test" )
@ContextConfiguration(
    locations = {
        "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml",
        "file:src/main/webapp/WEB-INF/spring/spring-context-test.xml",
        "file:src/main/webapp/WEB-INF/spring/security.xml"
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
    @Resource
    private WebApplicationContext webApplicationContext;


    /// *** Methods     *** ///
    /**
     * Prepare environment to run tests
     */
    @Before
    public void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(
            this.webApplicationContext
        )
            .dispatchOptions( true )
            .build();
    }
}

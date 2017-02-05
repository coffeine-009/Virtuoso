/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 9/21/14 6:27 PM
 */

package com.thecoffeine.virtuoso.module.controller;

import org.junit.Rule;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.security.oauth2.client.test.OAuth2ContextSetup;
import org.springframework.security.oauth2.client.test.RestTemplateHolder;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.web.client.RestOperations;

/**
 * Abstract test for rest controllers.
 *
 * @version 1.0
 */
@SqlGroup({
    @Sql( executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:init.sql" ),
    @Sql( executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:clean.sql" )
})
public abstract class AbstractRestControllerTest extends AbstractControllerTest implements RestTemplateHolder {

    private RestOperations restOperations = new TestRestTemplate();

    @Rule
    public OAuth2ContextSetup contextSetup = OAuth2ContextSetup.standard( this );


    /// *** Methods     *** ///
    /**
     * Prepare environment to run tests.
     */
    @Override
    public void tearUp() {

        //- Init mocks -//
        MockitoAnnotations.initMocks( this );
    }

    public RestOperations getRestTemplate() {
        return restOperations;
    }

    public void setRestTemplate( RestOperations restOperations ) {
        this.restOperations = restOperations;
    }
}

class MyDetails extends ResourceOwnerPasswordResourceDetails {
    public MyDetails(final Object obj) {
        setAccessTokenUri("/oauth/token");
        setClientId("myclientwith");
        setUsername("user");
        setPassword("password");
    }
}

/**
 * @copyright (c) 2014, by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 9/21/14 6:27 PM :: 9/28/14 8:06 PM
 *
 * @address /Ukraine/Ivano-Frankivsk
 */

package com.coffeine.virtuoso.module.controller;

import com.coffeine.virtuoso.security.model.entity.AuthenticationToken;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

/**
 * Abstract test for rest controllers
 *
 * @version 1.0
 */
public abstract class AbstractRestControllerTest extends AbstractControllerTest {

    /**
     * Mocked session
     */
    protected MockHttpSession session;

    /**
     * Mock for security context
     */
    public static class MockSecurityContext implements SecurityContext {

        private Authentication authentication;

        public MockSecurityContext(Authentication authentication) {
            this.authentication = authentication;
        }

        @Override
        public Authentication getAuthentication() {
            return authentication;
        }

        @Override
        public void setAuthentication(Authentication authentication) {
            this.authentication = authentication;
        }
    }


    /// *** Methods     *** ///
    /**
     * Prepare environment to run tests
     */
    @Override
    public void tearUp() {
        super.tearUp();

        AuthenticationToken authenticationToken = new AuthenticationToken(
            "user@virtuoso.com",
            "P@$$w0rd",
            null
        );
        this.session = new MockHttpSession();
            session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                new MockSecurityContext(authenticationToken)
            );

        //- Init mocks -//
        MockitoAnnotations.initMocks( this );
    }
}

/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 11/30/15 11:14 PM
 */

package com.coffeine.virtuoso.security.controller;

import com.coffeine.virtuoso.module.controller.AbstractRestControllerTest;
import com.coffeine.virtuoso.module.user.model.persistence.mock.RoleMock;
import com.coffeine.virtuoso.module.user.model.persistence.mock.UserMock;
import com.coffeine.virtuoso.notification.model.entity.Contact;
import com.coffeine.virtuoso.security.model.entity.User;
import com.coffeine.virtuoso.security.model.service.AccessRecoveryService;
import com.coffeine.virtuoso.security.model.service.RoleService;
import com.coffeine.virtuoso.security.model.service.UserService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for Security controller.
 * @see SecurityController
 *
 * @version 1.0
 */
public class SecurityControllerTest extends AbstractRestControllerTest {

    /// *** Properties  *** ///
    @Mock
    private ShaPasswordEncoder passwordEncoder;

    @Mock
    private RoleService roleService;

    @Mock
    private UserService userService;

    @Mock
    private AccessRecoveryService accessRecoveryService;

    @InjectMocks
    private SecurityController securityController = new SecurityController();


    /// *** Methods     *** ///
    /**
     * Init environment for run test
     */
    @Before
    @Override
    public void tearUp() {

        super.tearUp();

        //- Set up application -//
        this.mockMvc = MockMvcBuilders.standaloneSetup( securityController ).build();
    }

    /**
     * Reset environment to previous state
     */
    @After
    public void tearDown() {

    }


    /**
     * Test for sign up a new user.
     *
     * @throws Exception
     */
    @Test
    public void testSignupActionSuccess() throws Exception {

        //- Mock service -//
        when( this.roleService.findByCodes( anyList() ) ).thenReturn( RoleMock.findByCodes() );
        when( this.userService.create( any( User.class ) ) ).thenReturn( UserMock.create() );
        when( this.passwordEncoder.encodePassword( anyString(), any() ) ).thenReturn( "Te$t" );

        //- Do Sign Up request -//
        this.mockMvc.perform(
            post( "/security/signup" )
                .contentType( MediaType.APPLICATION_JSON )
                .content(
                    "{" +
                        "\"username\": \"unit@test.com\", " +
                        "\"password\": \"Te$t\", " +
                        "\"firstName\": \"Unit\", " +
                        "\"lastName\": \"test\", " +
                        "\"gender\": false, " +
                        "\"locale\": \"en-US\", " +
                        "\"roles\": [" +
                            "\"POET\"" +
                        "], " +
                        "\"birthday\": \"1990-08-10\"" +
                    "}"
                )
        ).andDo(print())
            .andExpect( status().isCreated() )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andExpect( jsonPath( "$.roles[0].code", notNullValue() ) );
            //TODO: finish
    }

    /**
     * Test for sign up a new user with invalid input.
     *
     * @throws Exception
     */
    @Test
    public void testSignupActionFailure() throws Exception {

        //- Mock service -//
        when( this.roleService.findByCodes( anyList() ) ).thenReturn( RoleMock.findByCodes() );
        when( this.userService.create( any( User.class ) ) ).thenReturn( UserMock.create() );
        when( this.passwordEncoder.encodePassword( anyString(), any() ) ).thenReturn( "Te$t" );

        //- Do Sign Up request -//
        this.mockMvc.perform(
            post("/security/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{" +
                        "\"username\": \"unit#test.com\", " +
                        "\"password\": \"Te$t\"" +
                    "}"
                )
        )
            .andExpect( status().isBadRequest() )
            .andDo( print() );
            //TODO: finish
    }

    /**
     * Test for success of forgotPassword action.
     *
     * @throws Exception
     */
    @Test
    public void testForgotPasswordActionSuccess() throws Exception {

        doNothing().when( this.accessRecoveryService ).lostAccess( any( Contact.class ) );

        this.mockMvc.perform(
            post( "/security/forgotPassword" )
                .contentType( MediaType.APPLICATION_JSON )
                .content(
                    "{" +
                        "\"email\": \"unit@test.com\"" +
                    "}"
                )
        ).andDo( print() )
            .andExpect( status().isOk() );
    }

    /**
     * Test for failure of forgotPassword action.
     *
     * @throws Exception
     */
    @Test
    public void testForgotPasswordActionFailure() throws Exception {

        //- Perform request -//
        this.mockMvc.perform(
            post( "/security/forgotPassword" )
                .contentType( MediaType.APPLICATION_JSON )
                .content(
                    "{" +
                        "\"email\": \"unit#test.com\"" +
                    "}"
                )
        ).andDo( print() )
            .andExpect( status().isBadRequest() );
    }

    /**
     * Test for failure of forgotPassword action.
     *
     * @throws Exception
     */
    @Test
    public void testForgotPasswordActionFailureEmail() throws Exception {

        doThrow( IllegalArgumentException.class ).when( this.accessRecoveryService )
            .lostAccess( any( Contact.class ) );

        //- Perform request -//
        this.mockMvc.perform(
            post( "/security/forgotPassword" )
                .contentType( MediaType.APPLICATION_JSON )
                .content(
                    "{" +
                        "\"email\": \"unit-non-exists@test.com\"" +
                    "}"
                )
        ).andDo( print() )
            .andExpect( status().isNotFound() );
    }
}

/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 11/29/15 12:10 AM
 *
 */

package com.coffeine.virtuoso.module.security.controller;

import com.coffeine.virtuoso.module.controller.AbstractRestControllerTest;
import com.coffeine.virtuoso.module.user.model.entity.User;
import com.coffeine.virtuoso.module.user.model.persistence.mock.RoleMock;
import com.coffeine.virtuoso.module.user.model.persistence.mock.UserMock;
import com.coffeine.virtuoso.module.user.model.service.RoleService;
import com.coffeine.virtuoso.module.user.model.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;
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
        )
            .andExpect( status().isCreated() )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andExpect( jsonPath( "$.roles[0].code", notNullValue() ) )
            .andDo( print() );
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
}

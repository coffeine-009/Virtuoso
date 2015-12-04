/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 11/30/15 11:14 PM
 */
package com.coffeine.virtuoso.security.controller;

import com.coffeine.virtuoso.module.controller.AbstractControllerTest;
import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test security controller
 *
 * @version 1.0
 */
public class FunctionalSecurityControllerTest extends AbstractControllerTest {

    /// *** Methods     *** ///
    /**
     * Prepare environment for test security
     */
    @Before
    public void tearUp() {
        super.tearUp();
    }

    //- SECTION :: TEST -//
    @Test
    public void testRegistrationActionSuccess() throws Exception {

        //- Do Sign Up request -//
        this.mockMvc.perform(
            post( "/security/signup" )
                .contentType( MediaType.APPLICATION_JSON )
                .content(
                    "{" +
                        "\"username\": \"unit@test.com\", " +
                        "\"password\": \"Te$t\", " +
                        "\"firstName\": \"Unit\", " +
                        "\"lastName\": \"Test\", " +
                        "\"gender\": false, " +
                        "\"locale\": \"en-US\", " +
                        "\"roles\": [" +
                            "\"POET\"" +
                        "], " +
                        "\"birthday\": \"1990-08-10\"" +
                    "}"
                )
        ).andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id", notNullValue()))
            .andExpect( jsonPath("$.id", not(empty())) )
            .andExpect( jsonPath( "$.roles", notNullValue() ) )
            .andExpect(jsonPath("$.roles", not(empty())))
            .andExpect( jsonPath("$.roles[0].code", notNullValue()) )
            .andExpect( jsonPath( "$.roles[0].code", not(empty()) ) )
            .andExpect(jsonPath("$.roles[0].code").value("POET"))
            .andExpect( jsonPath("$.firstName", notNullValue()) )
            .andExpect(jsonPath("$.firstName", not(empty())))
            .andExpect( jsonPath( "$.firstName" ).value("Unit") )
            .andExpect(jsonPath("$.lastName", notNullValue()))
            .andExpect( jsonPath("$.lastName", not(empty())) )
            .andExpect(jsonPath("$.lastName").value("Test"))
            .andExpect(jsonPath("$.gender").value(false))
            .andExpect(jsonPath("$.locale").value("en-US"));
        //TODO: finish
    }

    @Test
    public void testRegistrationActionFailure() throws Exception {

        //- Do Sign Up request -//
        this.mockMvc.perform(
                post("/security/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                "{" +
                                        "\"username\": \"unit#test.com\", " +
                                        "\"password\": \"Te$t\", " +
                                        "\"firstName\": \"Unit\", " +
                                        "\"lastName\": \"test\", " +
                                        "\"gender\": false, " +
                                        "\"locale\": \"en-US\", " +
                                        "\"roles\": [" +
                                        "], " +
                                        "\"birthday\": \"1990-08-10\"" +
                                        "}"
                        )
        ).andDo(print())
            .andExpect( status().isBadRequest() )
            .andExpect( jsonPath("$.fieldErrors", notNullValue()) )
            .andExpect(jsonPath("$.fieldErrors", not(empty())))
            ;
        //TODO: finish
    }

    @Test
    public void testRegistrationActionFailureMapping() throws Exception {

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
                        "], " +
                        "\"birthday\": \"1990-08/10\"" +
                    "}"
            )
        ).andDo(print())
            .andExpect( status().isBadRequest() )
            .andExpect( jsonPath( "$.fieldErrors", notNullValue() ) )
            .andExpect( jsonPath( "$.fieldErrors", not( empty() ) ) )
            ;
        //TODO: finish
    }

    @Test
    public void testSigninSuccess() throws Exception {

        //- Success -//
        this.mockMvc.perform(
            post( "/oauth/token" )
                .contentType( MediaType.APPLICATION_JSON )
                .header(
                    "Authorization",
                    "Basic " + new String(
                        Base64.encodeBase64(
                            "developer:developer32".getBytes()
                        )
                    )
                )
                .param( "grant_type", "password" )
                .param( "scope", "read" )
                .param( "clientId", "developer" )
                .param( "clientSecret", "developer32" )
                .param( "username", "user@virtuoso.com" )
                .param( "password", "123" )
        )
            .andExpect( status().isOk() )
            .andExpect( content().contentType( MediaType.APPLICATION_JSON + ";charset=UTF-8" ) )
            .andExpect( jsonPath( "$.access_token", notNullValue() ) )
            .andExpect( jsonPath( "$.access_token", not( empty() ) ) )
            .andExpect( jsonPath( "$.expires_in", notNullValue() ) )
            .andExpect( jsonPath( "$.expires_in", not( empty() ) ) )
            .andExpect( jsonPath( "$.token_type" ).value( "bearer" ) )
            .andExpect( jsonPath( "$.scope" ).value( "read" ) );
    }

    @Test
    public void testSigninFailure() throws Exception {

        //- Success -//
        this.mockMvc.perform(
            post( "/oauth/token" )
                .contentType( MediaType.APPLICATION_JSON )
                .header(
                    "Authorization",
                    "Basic " + new String(
                        Base64.encodeBase64(
                            "developer:developer32".getBytes()
                        )
                    )
                )
                .param( "grant_type", "password" )
                .param( "scope", "read" )
                .param( "clientId", "developer" )
                .param( "clientSecret", "developer32" )
                .param( "username", "user@virtuoso.com" )
                .param( "password", "1234" )
        )
            .andExpect( status().isBadRequest() )
            .andExpect( content().contentType( MediaType.APPLICATION_JSON + ";charset=UTF-8" ) )
            .andExpect( jsonPath( "$.error", notNullValue() ) )
            .andExpect( jsonPath( "$.error", not( empty() ) ) )
            .andExpect( jsonPath( "$.error" ).value( "invalid_grant" ) )
            .andExpect( jsonPath( "$.error_description", notNullValue() ) )
            .andExpect( jsonPath( "$.error_description", not( empty() ) ) )
            .andExpect( jsonPath( "$.error_description" ).value( "Bad user credentials" ) );
        //TODO: finish
    }

    /**
     * Test for success of forgotPassword action.
     *
     * @throws Exception
     */
    @Test
    public void testForgotPasswordActionSuccess() throws Exception {

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
            .andExpect( status().isBadRequest() )
            .andExpect( content().contentType( MediaType.APPLICATION_JSON + ";charset=UTF-8" ) )
            .andExpect( jsonPath( "$.fieldErrors", notNullValue() ) )
            .andExpect( jsonPath( "$.fieldErrors", not( empty() ) ) )
            .andExpect( jsonPath( "$.fieldErrors[0].field", notNullValue() ) )
            .andExpect( jsonPath( "$.fieldErrors[0].field", not( empty() ) ) )
            .andExpect( jsonPath( "$.fieldErrors[0].field" ).value( "email" ) )
            .andExpect( jsonPath( "$.fieldErrors[0].message", notNullValue() ) )
            .andExpect( jsonPath( "$.fieldErrors[0].message", not( empty() ) ) )
            .andExpect( jsonPath( "$.fieldErrors[0].message" ).value( "not a well-formed email address" ) );
    }

    /**
     * Test for failure of forgotPassword action.
     *
     * @throws Exception
     */
    @Test
    public void testForgotPasswordActionFailureEmail() throws Exception {

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

/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 11/30/15 11:14 PM
 */

package com.coffeine.virtuoso.security.controller;

import com.coffeine.virtuoso.module.controller.AbstractControllerTest;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.apache.commons.codec.binary.Base64;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test security controller.
 *
 * @version 1.0
 */
public class FunctionalSecurityControllerTest extends AbstractControllerTest {

    //- Mock SMTP server -//
    private static final GreenMail smtpServer = new GreenMail( new ServerSetup( 2525, null, "smtp" ) );

    /// *** Methods     *** ///
    /**
     * Prepare environment for test security.
     */
    @Before
    public void tearUp() {
        super.tearUp();

        //- Turn on SMTP Server -//
        smtpServer.start();
    }

    /**
     * Clear environment.
     */
    @After
    public void tearDown() {
        //- Turn on SMTP Server -//
        smtpServer.stop();
    }

    //- SECTION :: TEST -//
    /**
     * Test successful registration attempt.
     *
     * @throws Exception
     */
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
        )
            .andExpect( status().isCreated() )
            .andExpect( jsonPath( "$.id", notNullValue() ) )
            .andExpect( jsonPath( "$.id", not( empty() ) ) )
            .andExpect( jsonPath( "$.roles", notNullValue() ) )
            .andExpect( jsonPath( "$.roles", not( empty() ) ) )
            .andExpect( jsonPath( "$.roles[0].code", notNullValue() ) )
            .andExpect( jsonPath( "$.roles[0].code", not( empty() ) ) )
            .andExpect( jsonPath( "$.roles[0].code" ).value( "POET" ) )
            .andExpect( jsonPath( "$.firstName", notNullValue() ) )
            .andExpect( jsonPath( "$.firstName", not( empty() ) ) )
            .andExpect( jsonPath( "$.firstName" ).value( "Unit" ) )
            .andExpect( jsonPath( "$.lastName", notNullValue() ) )
            .andExpect( jsonPath( "$.lastName", not( empty() ) ) )
            .andExpect( jsonPath( "$.lastName" ).value( "Test" ) )
            .andExpect( jsonPath( "$.gender" ).value( false ) )
            .andExpect( jsonPath( "$.locale" ).value( "en-US" ) )
            .andExpect( jsonPath( "$.poet", notNullValue() ) )
            .andExpect( jsonPath( "$.poet", not( empty() ) ) )
            .andExpect( jsonPath( "$.poet.birthday", notNullValue() ) )
            .andExpect( jsonPath( "$.poet.birthday", not( empty() ) ) )
//            .andExpect( jsonPath( "$.poet.birthday" ).value( "1990-08-10" ) )//FIXME: Configure JsonSerializer for LocalDate
            .andExpect( jsonPath( "$.poet.deathDate", nullValue() ) )
            .andDo(
                document(
                    "signup-example",
                    requestFields(
                        fieldWithPath( "username" ).description( "E-mail of new user." ),
                        fieldWithPath( "password" ).description( "Password of new user." ),
                        fieldWithPath( "firstName" ).description( "First name of new user." ),
                        fieldWithPath( "lastName" ).description( "Las name of new user." ),
                        fieldWithPath( "gender" ).description( "Gender of new user." ),
                        fieldWithPath( "locale" ).description( "Locale of new user." ),
                        fieldWithPath( "roles" ).description( "List of roles of new user." ),
                        fieldWithPath( "birthday" ).description( "Birthday of new user." )
                    )
                )
            );

        //- Check if notification was sent -//
        assertEquals( "Count of messages does not matches", 1, smtpServer.getReceivedMessages().length );
        //TODO: Add checking email.
    }

    /**
     * Test unsuccessful registration attempt.
     *
     * @throws Exception
     */
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
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.fieldErrors", notNullValue()))
            .andExpect(jsonPath("$.fieldErrors", not(empty())))
        ;
        //TODO: finish
    }

    /**
     * Test unsuccessful registration attempt.
     * Bad date format.
     *
     * @throws Exception
     */
    @Test
    public void testRegistrationActionFailureMapping() throws Exception {

        //- Do Sign Up request -//
        this.mockMvc.perform(
            post("/security/signup")
                .contentType(MediaType.APPLICATION_JSON)
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
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.fieldErrors", notNullValue()))
            .andExpect(jsonPath("$.fieldErrors", not(empty())))
        ;
        //TODO: finish
    }

    /**
     * Test successful sign in attempt.
     *
     * @throws Exception
     */
    @Test
    public void testSignInSuccess() throws Exception {

        //- Success -//
        this.mockMvc.perform(
            post( "/oauth/token" )
                .contentType( MediaType.APPLICATION_FORM_URLENCODED )
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
            .andExpect( jsonPath( "$.token_type", notNullValue() ) )
            .andExpect( jsonPath( "$.token_type", not( empty() ) ) )
            .andExpect( jsonPath( "$.token_type" ).value( "bearer" ) )
            .andExpect( jsonPath( "$.scope", notNullValue() ) )
            .andExpect( jsonPath( "$.scope", not( empty() ) ) )
            .andExpect( jsonPath( "$.scope" ).value( "read" ) )
            .andDo(
                document(
                    "sign-in-example",
                    requestParameters(
                        parameterWithName( "username" ).description( "E-mail of new user." ),
                        parameterWithName( "password" ).description( "Password of new user." ),
                        parameterWithName( "clientId" ).description( "Client(app) id." ),
                        parameterWithName( "clientSecret" ).description( "Client(app) password." ),
                        parameterWithName( "scope" ).description( "Scope." ),
                        parameterWithName( "grant_type" ).description( "Grant type." )
                    ),
                    responseFields(
                        fieldWithPath( "access_token" ).description( "Token for access to private API." ),
                        fieldWithPath( "refresh_token" ).description( "Token for refresh access token to private API." ),
                        fieldWithPath( "scope" ).description( "Token scope." ),
                        fieldWithPath( "token_type" ).description( "Type of token." ),
                        fieldWithPath( "expires_in" ).description( "Time of expiring." )
                    )
                )
            );
    }

    /**
     * Test unsuccessful sign in attempt.
     * Bad credentials.
     *
     * @throws Exception
     */
    @Test
    public void testSignInFailure() throws Exception {

        //- Success -//
        this.mockMvc.perform(
            post( "/oauth/token" )
                .contentType( MediaType.APPLICATION_FORM_URLENCODED )
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
                .param( "username", "new.user@virtuoso.com" )
                .param( "password", "1234" )
        )
            .andExpect( status().isBadRequest() )
            .andExpect( content().contentType( MediaType.APPLICATION_JSON + ";charset=UTF-8" ) )
            .andExpect( jsonPath( "$.error", notNullValue() ) )
            .andExpect( jsonPath( "$.error", not( empty() ) ) )
            .andExpect( jsonPath( "$.error" ).value( "invalid_grant" ) )
            .andExpect( jsonPath( "$.error_description", notNullValue() ) )
            .andExpect( jsonPath( "$.error_description", not( empty() ) ) )
            .andExpect( jsonPath( "$.error_description" ).value( "Bad user credentials" ) )
            .andDo(
                document(
                    "sign-in-fail-example",
                    requestParameters(
                        parameterWithName( "username" ).description( "E-mail of new user." ),
                        parameterWithName( "password" ).description( "Password of new user." ),
                        parameterWithName( "clientId" ).description( "Client(app) id." ),
                        parameterWithName( "clientSecret" ).description( "Client(app) password." ),
                        parameterWithName( "scope" ).description( "Scope." ),
                        parameterWithName( "grant_type" ).description( "Grant type." )
                    )
                )
            );
    }

    /**
     * Test for success of forgotPassword action.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testForgotPasswordActionSuccess() throws Exception {

        //- Perform -//
        this.mockMvc.perform(
            post("/security/forgotPassword")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{" +
                        "\"email\": \"unit@test.com\"" +
                        "}"
                )
        ).andDo(print())
            .andExpect(status().isOk());


        assertEquals(1, smtpServer.getReceivedMessages().length);
    }

    /**
     * Test for failure of forgotPassword action.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testForgotPasswordActionFailure() throws Exception {

        //- Perform request -//
        this.mockMvc.perform(
            post("/security/forgotPassword")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{" +
                        "\"email\": \"unit#test.com\"" +
                        "}"
                )
        ).andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"))
            .andExpect(jsonPath("$.fieldErrors", notNullValue()))
            .andExpect(jsonPath("$.fieldErrors", not(empty())))
            .andExpect(jsonPath("$.fieldErrors[0].field", notNullValue()))
            .andExpect(jsonPath("$.fieldErrors[0].field", not(empty())))
            .andExpect(jsonPath("$.fieldErrors[0].field").value("email"))
            .andExpect(jsonPath("$.fieldErrors[0].message", notNullValue()))
            .andExpect(jsonPath("$.fieldErrors[0].message", not(empty())))
            .andExpect(jsonPath("$.fieldErrors[0].message").value("not a well-formed email address"));
    }

    /**
     * Test for failure of forgotPassword action.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testForgotPasswordActionFailureEmail() throws Exception {

        //- Perform request -//
        this.mockMvc.perform(
            post("/security/forgotPassword")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{" +
                        "\"email\": \"unit-non-exists@test.com\"" +
                        "}"
                )
        ).andDo(print())
            .andExpect(status().isNotFound());
    }
}

/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:49 PM
 */

package com.coffeine.virtuoso.music.controller;

import com.coffeine.virtuoso.module.controller.AbstractRestControllerTest;
import com.coffeine.virtuoso.module.util.TypeHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Functional tests for StyleController.
 * @see StyleController
 */
public class FunctionalStyleControllerTest extends AbstractRestControllerTest {

    /**
     * Init environment for run test.
     */
    @Before
    @Override
    public void tearUp() {

        super.tearUp();
    }

    /**
     * Clean environment.
     */
    @After
    @Override
    public void tearDown() {
        //- Clean environment after run tests -//
    }


    /**
     * Test of getting list of styles.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testListActionSuccess() throws Exception {
        // Success. Get list of styles
        final ResultActions resultActions = this.mockMvc.perform(
            get( "/music/styles?page={page}&limit={limit}", 1, 10 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
        );

        resultActions
            .andExpect( status().isOk() )
            .andDo(
                document(
                    "styles-list-example",
                    responseFields(
                        fieldWithPath( "[].id" ).description( "Id of style." ),
                        fieldWithPath( "[].code" ).description( "Code of style." ),
                        fieldWithPath( "[].title" ).description( "Title of style." ),
                        fieldWithPath( "[].description" ).description( "Description of style." )
                    )
                )
            );

        TypeHelper.check( resultActions, "WALTZ", "Waltz", "Waltz." );
    }

    /**
     * Test of getting style by id.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testRetrieveActionSuccess() throws Exception {
        // Success. Get list of styles
        this.mockMvc.perform(
            get( "/music/styles/{id}", 1 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
        )
            .andExpect( status().isOk() )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andExpect( jsonPath( "$id", notNullValue() ) )
            .andExpect( jsonPath( "$id").value( 1 ) )
            .andExpect( jsonPath( "$code", notNullValue() ) )
            .andExpect( jsonPath( "$code" ).value( "WALTZ" ) )
            .andExpect( jsonPath( "$title", notNullValue() ) )
            .andExpect( jsonPath( "$title" ).value( "Waltz" ) )
            .andExpect( jsonPath( "$description", notNullValue() ) )
            .andExpect( jsonPath( "$description" ).value(  "Waltz." ) )
            .andDo(
                document(
                    "styles-retrieve-success-example",
                    responseFields(
                        fieldWithPath( "id" ).description( "Id of style." ),
                        fieldWithPath( "code" ).description( "Code of style." ),
                        fieldWithPath( "title" ).description( "Title of style." ),
                        fieldWithPath( "description" ).description( "Description of style." )
                    )
                )
            );
    }

    /**
     * Test of getting style by id.
     * Failure.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testRetrieveActionFailure() throws Exception {
        // Success. Get list of styles
        this.mockMvc.perform(
            get( "/music/styles/{id}", 999999 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
        )
            .andExpect( status().isNotFound() )
            .andDo( document( "styles-retrieve-failure-example" ) );
    }

    /**
     * Test of creating a new style.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testCreateActionSuccess() throws Exception {
        //- Success. Create a new style -//
        this.mockMvc.perform(
            post( "/music/styles" )
                .header( "Authorization", this.session.getAuthorizationHeader() )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"code\": \"POLKA\"," +
                        "\"title\": \"Polka\"," +
                        "\"description\": \"Polka.\"" +
                    "}"
                )
        )
            .andExpect( status().isCreated() )
            .andExpect( jsonPath( "$id", notNullValue() ) )
            .andExpect( jsonPath( "$code", notNullValue() ) )
            .andExpect( jsonPath( "code" ).value( "POLKA" ) )
            .andExpect( jsonPath( "$title", notNullValue() ) )
            .andExpect( jsonPath( "title" ).value( "Polka" ) )
            .andExpect( jsonPath( "$description", notNullValue() ) )
            .andExpect( jsonPath( "description" ).value( "Polka." ) )
            .andDo(
                document(
                    "styles-create-success-example",
                    requestFields(
                        fieldWithPath( "code" ).description( "Code of style." ),
                        fieldWithPath( "title" ).description( "Title of style." ),
                        fieldWithPath( "description" ).description( "Description of style." )
                    ),
                    responseFields(
                        fieldWithPath( "id" ).description( "Id of style." ),
                        fieldWithPath( "code" ).description( "Code of style." ),
                        fieldWithPath( "title" ).description( "Title of style." ),
                        fieldWithPath( "description" ).description( "Description of style." )
                    )
                )
            );
    }

    /**
     * Test of creating a new style.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testCreateActionFailure() throws Exception {
        //- Failure. Create a new style -//
        this.mockMvc.perform(
            post( "/music/styles" )
                .header( "Authorization", this.session.getAuthorizationHeader() )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"code\": \"WALTZ\"," +
                        "\"title\": \"Waltz\"," +
                        "\"description\": \"Waltz.\"" +
                    "}"
                )
        )
//            .andExpect( status().isConflict() );//FIXME: unique constraint
            .andDo( document( "styles-create-failure-example" ) );
    }

    /**
     * Test of updating a new style.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testUpdateActionSuccess() throws Exception {
        //- Success. Update a new style -//
        this.mockMvc.perform(
            put( "/music/styles/{id}", 1 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"code\": \"WALTZ\"," +
                        "\"title\": \"Waltz\"," +
                        "\"description\": \"Waltz. Good style.\"" +
                    "}"
                )
        )
            .andExpect( status().isOk() )
            .andExpect( jsonPath( "$id", notNullValue() ) )
            .andExpect( jsonPath( "$code", notNullValue() ) )
            .andExpect( jsonPath( "$code" ).value( "WALTZ" ) )
            .andExpect( jsonPath( "$title", notNullValue() ) )
            .andExpect( jsonPath( "$title" ).value( "Waltz" ) )
            .andExpect( jsonPath( "$description", notNullValue() ) )
            .andExpect( jsonPath( "$description" ).value( "Waltz. Good style." ) )
            .andDo(
                document(
                    "styles-update-success-example",
                    requestFields(
                        fieldWithPath( "code" ).description( "Code of style." ),
                        fieldWithPath( "title" ).description( "Title of style." ),
                        fieldWithPath( "description" ).description( "Description of style." )
                    ),
                    responseFields(
                        fieldWithPath( "id" ).description( "Id of style." ),
                        fieldWithPath( "code" ).description( "Code of style." ),
                        fieldWithPath( "title" ).description( "Title of style." ),
                        fieldWithPath( "description" ).description( "Description of style." )
                    )
                )
            );
    }

    /**
     * Test of updating a new style.
     * Failure.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testUpdateActionFailure() throws Exception {
        //- Failure. Update a new style -//
        this.mockMvc.perform(
            put( "/music/styles/{id}", 99999 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"code\": \"WALTZ\"," +
                        "\"title\": \"Waltz\"," +
                        "\"description\": \"Waltz. Good style.\"" +
                    "}"
                )
        )
            .andExpect( status().isNotFound() )
            .andDo( document( "styles-update-failure-example" ) );
    }

    /**
     * Test of deleting a new style.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testDeleteActionSuccess() throws Exception {
        //- Success. Delete a style by id -//
        this.mockMvc.perform(
            delete( "/music/styles/{id}", 1 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
        )
            .andExpect( status().isOk() )
            .andDo( document( "styles-delete-success-example" ) );
    }

    /**
     * Test of deleting a new style.
     * Failure.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testDeleteActionFailure() throws Exception {
        //- Failure. Delete a style by id -//
        this.mockMvc.perform(
            delete( "/music/styles/{id}", 999999 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
        )
            .andExpect( status().isNotFound() )
            .andDo( document( "styles-delete-failure-example" ) );
    }
}

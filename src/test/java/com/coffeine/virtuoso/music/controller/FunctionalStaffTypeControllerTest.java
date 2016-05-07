/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/8/15 9:00 PM
 */

package com.coffeine.virtuoso.music.controller;

import com.coffeine.virtuoso.module.controller.AbstractRestControllerTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
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
 * Tests for FunctionalStaffTypeController.
 *
 * @version 1.0
 */
public class FunctionalStaffTypeControllerTest extends AbstractRestControllerTest {

    /**
     * Prepare environment to run tests.
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
     * Test get list of staff types.
     *
     * @throws Exception
     */
    @Test
    public void testListActionSuccess() throws Exception {

        // Success. Get list of styles
        this.mockMvc.perform(
            get( "/music/staffs/types?page={page}&limit={limit}", 1, 10 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
        )
            .andExpect( status().isOk() )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andExpect( jsonPath( "$", hasSize( 1 ) ) )
            .andExpect( jsonPath( "$[*].id", notNullValue() ) )
            .andExpect( jsonPath( "$[*].id", containsInAnyOrder( 1 ) ) )
            .andExpect( jsonPath( "$[*].code", notNullValue() ) )
            .andExpect( jsonPath( "$[*].code", containsInAnyOrder( "TAB" ) ) )
            .andExpect( jsonPath( "$[*].title", notNullValue() ) )
            .andExpect( jsonPath( "$[*].title", containsInAnyOrder( "Tabs" ) ) )
            .andExpect( jsonPath( "$[*].description", notNullValue() ) )
            .andExpect( jsonPath( "$[*].description", containsInAnyOrder( "Tabulatures." ) ) )
            .andDo(
                document(
                    "staff-types-list-example",
                    responseFields(
                        fieldWithPath( "[].id" ).description( "Id of staff type." ),
                        fieldWithPath( "[].code" ).description( "Code of staff type." ),
                        fieldWithPath( "[].title" ).description( "Title of staff type." ),
                        fieldWithPath( "[].description" ).description( "Description of staff type." )
                    )
                )
            );
    }

    /**
     * Test get staff type.
     * Success.
     *
     * @throws Exception
     */
    @Test
    public void testFindActionSuccess() throws Exception {

        // Success. Get list of styles
        this.mockMvc.perform(
            get( "/music/staffs/types/{id}", 1 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
        )
            .andExpect( status().isOk() )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andExpect( jsonPath( "$id", notNullValue() ) )
            .andExpect( jsonPath( "$id").value( 1 ) )
            .andExpect( jsonPath( "$code", notNullValue() ) )
            .andExpect( jsonPath( "$code" ).value( "TAB" ) )
            .andExpect( jsonPath( "$title", notNullValue() ) )
            .andExpect( jsonPath( "$title" ).value( "Tabs" ) )
            .andExpect( jsonPath( "$description", notNullValue() ) )
            .andExpect( jsonPath( "$description" ).value(  "Tabulatures." ) )
            .andDo(
                document(
                    "staff-types-retrieve-success-example",
                    responseFields(
                        fieldWithPath( "id" ).description( "Id of staff type." ),
                        fieldWithPath( "code" ).description( "Code of staff type." ),
                        fieldWithPath( "title" ).description( "Title of staff type." ),
                        fieldWithPath( "description" ).description( "Description of staff type." )
                    )
                )
            );
    }

    /**
     * Test get staff type.
     * Failure.
     *
     * @throws Exception
     */
    @Test
    public void testFindActionFailure() throws Exception {

        // Success. Get list of styles
        this.mockMvc.perform(
            get( "/music/staffs/types/{id}", 999999 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
        )
            .andExpect( status().isNotFound() );
    }

    /**
     * Test create a staff type.
     * Success.
     *
     * @throws Exception
     */
    @Test
    public void testCreateActionSuccess() throws Exception {

        // Success. Get list of styles
        this.mockMvc.perform(
            post( "/music/staffs/types" )
                .header( "Authorization", this.session.getAuthorizationHeader() )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"code\": \"TAB\"," +
                        "\"title\": \"Tabulature\"," +
                        "\"description\": \"Tabs.\"" +
                    "}"
                )
        )
            .andExpect( status().isCreated() )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andExpect( jsonPath( "$id", notNullValue() ) )
            .andExpect( jsonPath( "$code", notNullValue() ) )
            .andExpect( jsonPath( "$code" ).value( "TAB" ) )
            .andExpect( jsonPath( "$title", notNullValue() ) )
            .andExpect( jsonPath( "$title" ).value( "Tabulature" ) )
            .andExpect( jsonPath( "$description", notNullValue() ) )
            .andExpect( jsonPath( "$description" ).value(  "Tabs." ) )
            .andDo(
                document(
                    "staff-types-create-success-example",
                    requestFields(
                        fieldWithPath( "code" ).description( "Code of staff type." ),
                        fieldWithPath( "title" ).description( "Title of staff type." ),
                        fieldWithPath( "description" ).description( "Description of staff type." )
                    ),
                    responseFields(
                        fieldWithPath( "id" ).description( "Id of staff type." ),
                        fieldWithPath( "code" ).description( "Code of staff type." ),
                        fieldWithPath( "title" ).description( "Title of staff type." ),
                        fieldWithPath( "description" ).description( "Description of staff type." )
                    )
                )
            );
    }

    /**
     * Test create a staff type.
     * Failure.
     *
     * @throws Exception
     */
    @Test
    public void testCreateActionFailure() throws Exception {

        // Success. Get list of styles
        this.mockMvc.perform(
            post( "/music/staffs/types" )
                .header( "Authorization", this.session.getAuthorizationHeader() )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"code\": \"TAB\"," +
                        "\"title\": \"Tabulature\"," +
                        "\"description\": \"Tabs.\"" +
                    "}"
                )
        )
            .andExpect( status().isCreated() )//FIXME: 409
            .andDo( document( "staff-types-create-failure-example" ) );
    }

    /**
     * Test update a staff type.
     * Success.
     *
     * @throws Exception
     */
    @Test
    public void testUpdateActionSuccess() throws Exception {

        // Success. Get list of styles
        this.mockMvc.perform(
            put( "/music/staffs/types/{id}", 1 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"code\": \"TAB\"," +
                        "\"title\": \"Tablature\"," +
                        "\"description\": \"Tablature.\"" +
                    "}"
                )
        )
            .andExpect( status().isOk() )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andExpect( jsonPath( "$id", notNullValue() ) )
            .andExpect( jsonPath( "$id").value( 1 ) )
            .andExpect( jsonPath( "$code", notNullValue() ) )
            .andExpect( jsonPath( "$code" ).value( "TAB" ) )
            .andExpect( jsonPath( "$title", notNullValue() ) )
            .andExpect( jsonPath( "$title" ).value( "Tablature" ) )
            .andExpect( jsonPath( "$description", notNullValue() ) )
            .andExpect( jsonPath( "$description" ).value(  "Tablature." ) )
            .andDo(
                document(
                    "staff-types-update-success-example",
                    requestFields(
                        fieldWithPath( "code" ).description( "Code of staff type." ),
                        fieldWithPath( "title" ).description( "Title of staff type." ),
                        fieldWithPath( "description" ).description( "Description of staff type." )
                    ),
                    responseFields(
                        fieldWithPath( "id" ).description( "Id of staff type." ),
                        fieldWithPath( "code" ).description( "Code of staff type." ),
                        fieldWithPath( "title" ).description( "Title of staff type." ),
                        fieldWithPath( "description" ).description( "Description of staff type." )
                    )
                )
            );
    }

    /**
     * Test update a staff type.
     * Failure.
     *
     * @throws Exception
     */
    @Test
    public void testUpdateActionFailure() throws Exception {

        // Success. Get list of styles
        this.mockMvc.perform(
            put( "/music/staffs/types/{id}", 99999 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"code\": \"TAB\"," +
                        "\"title\": \"Tabulature\"," +
                        "\"description\": \"Tabs.\"" +
                    "}"
                )
        )
            .andExpect( status().isNotFound() )
            .andDo( document( "staff-types-update-failure-example" ) );
    }

    /**
     * Test delete a staff type.
     * Success.
     *
     * @throws Exception
     */
    @Test
    public void testDeleteActionSuccess() throws Exception {

        // Success. Get list of styles
        this.mockMvc.perform(
            delete( "/music/staffs/types/{id}", 1 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
        )
            .andExpect( status().isOk() )
            .andDo( document( "staff-types-delete-success-example" ) );
    }

    /**
     * Test delete a staff type.
     * Failure.
     *
     * @throws Exception
     */
    @Test
    public void testDeleteActionFailure() throws Exception {

        // Success. Get list of styles
        this.mockMvc.perform(
            delete( "/music/staffs/types/{id}", 9999 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
        )
            .andExpect( status().isNotFound() )
            .andDo( document( "staff-types-delete-failure-example" ) );
    }
}

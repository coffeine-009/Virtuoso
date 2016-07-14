/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/8/15 10:11 PM
 */

package com.coffeine.virtuoso.music.controller;

import com.coffeine.virtuoso.module.controller.AbstractRestControllerTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
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
 * Tests for StaffController.
 *
 * @version 1.0
 */
public class FunctionalStaffControllerTest extends AbstractRestControllerTest {

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
     * Get list of staffs.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testFindAllAction() throws Exception {
        //- Success -//
        this.mockMvc.perform(
            get( "/music/staffs?page={page}&limit={limit}", 1, 10 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
        )
            .andExpect( status().isOk() )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andExpect( jsonPath( "$", hasSize( 1 ) ) )
            .andExpect( jsonPath( "$[*].id", notNullValue() ) )
            .andExpect( jsonPath( "$[*].id", containsInAnyOrder( 1 ) ) )
            //- Composer -//
            .andExpect( jsonPath( "$[*].composers", notNullValue() ) )
            .andExpect( jsonPath( "$[*].composers", not( empty() ) ) )
            .andExpect( jsonPath( "$[*].composers", hasSize( 2 ) ) )
            .andExpect( jsonPath( "$[*].composers[*].id", containsInAnyOrder( 1 ) ) )
            .andExpect( jsonPath( "$[*].composers[*].data[*].firstName", containsInAnyOrder( "Test" ) ) )
            .andExpect( jsonPath( "$[*].composers[*].data[*].lastName", containsInAnyOrder( "Unit" ) ) )
            .andExpect( jsonPath( "$[*].composers[*].data[*].middleName", containsInAnyOrder( "Mockito" ) ) )
            .andExpect( jsonPath( "$[*].composers[*].data[*].locale", containsInAnyOrder( "uk-UA" ) ) )
            .andExpect( jsonPath( "$[*].composers[*].locale", containsInAnyOrder( "uk-UA" ) ) )
            .andExpect( jsonPath( "$[*].composers[*].gender", containsInAnyOrder( true ) ) )
            .andExpect( jsonPath( "$[*].composers[*].birthday", notNullValue() ) )
            .andExpect( jsonPath( "$[*].composers[0].deathDate", nullValue() ) )
            .andExpect( jsonPath( "$[*].staffType.id", notNullValue() ) )
            .andExpect( jsonPath( "$[*].staffType.id", containsInAnyOrder( 1 ) ) )
            .andExpect( jsonPath( "$[*].staffType.code", notNullValue() ) )
            .andExpect( jsonPath( "$[*].staffType.code", containsInAnyOrder( "TAB" ) ) )
            .andExpect( jsonPath( "$[*].staffType.title", notNullValue() ) )
            .andExpect( jsonPath( "$[*].staffType.title", containsInAnyOrder( "Tabs" ) ) )
            .andExpect( jsonPath( "$[*].staffType.description", notNullValue() ) )
            .andExpect( jsonPath( "$[*].staffType.description", containsInAnyOrder( "Tabulatures." ) ) )
            .andExpect( jsonPath( "$[*].style.id", notNullValue() ) )
            .andExpect( jsonPath( "$[*].style.id", containsInAnyOrder( 1 ) ) )
            .andExpect( jsonPath( "$[*].style.code", notNullValue() ) )
            .andExpect( jsonPath( "$[*].style.code", containsInAnyOrder( "WALTZ" ) ) )
            .andExpect( jsonPath( "$[*].style.title", notNullValue() ) )
            .andExpect( jsonPath( "$[*].style.title", containsInAnyOrder( "Waltz" ) ) )
            .andExpect( jsonPath( "$[*].style.description", notNullValue() ) )
            .andExpect( jsonPath( "$[*].style.description", containsInAnyOrder( "Waltz." ) ) )
            .andExpect( jsonPath( "$[*].locale", notNullValue() ) )
            .andExpect( jsonPath( "$[*].locale", containsInAnyOrder( "uk-UA" ) ) )
            .andExpect( jsonPath( "$[*].file", notNullValue() ) )
            .andExpect( jsonPath( "$[*].file", containsInAnyOrder( "ok\\n==============" ) ) )
            .andExpect( jsonPath( "$[*].creation", notNullValue() ) )
            .andDo(
                document(
                    "staffs-list-example",
                    responseFields(
                        fieldWithPath( "[].id" ).description( "Id of staff." ),
                        fieldWithPath( "[].composers" ).description( "List of composers." ),
                        fieldWithPath( "[].staffType.id" ).description( "Id of staff type." ),
                        fieldWithPath( "[].staffType.code" ).description( "Code of staff type." ),
                        fieldWithPath( "[].staffType.title" ).description( "Title of staff type." ),
                        fieldWithPath( "[].staffType.description" ).description( "Description of staff type." ),
                        fieldWithPath( "[].style.id" ).description( "Id of style type." ),
                        fieldWithPath( "[].style.code" ).description( "Code of style type." ),
                        fieldWithPath( "[].style.title" ).description( "Title of style type." ),
                        fieldWithPath( "[].style.description" ).description( "Description of style type." ),
                        fieldWithPath( "[].locale" ).description( "Locale of the staff." ),
                        fieldWithPath( "[].file" ).description( "File of the staff." ),
                        fieldWithPath( "[].creation" ).description( "Creation date of the staff." )
                    )
                )
            );
    }

    /**
     * Get a staff.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testFindActionSuccess() throws Exception {
        //- Success -//
        this.mockMvc.perform(
            get( "/music/staffs/{id}", 1 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
        )
            .andExpect( status().isOk() )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andExpect( jsonPath( "$id", notNullValue() ) )
            .andExpect( jsonPath( "$id" ).value( 1 ) )
            //- Composer -//
            .andExpect( jsonPath( "$composers", notNullValue() ) )
            .andExpect( jsonPath( "$composers", not( empty() ) ) )
            .andExpect( jsonPath( "$composers", hasSize( 1 ) ) )
            .andExpect( jsonPath( "$composers[*].id", containsInAnyOrder( 1 ) ) )
            .andExpect( jsonPath( "$composers[*].data[*].firstName", containsInAnyOrder( "Test" ) ) )
            .andExpect( jsonPath( "$composers[*].data[*].lastName", containsInAnyOrder( "Unit" ) ) )
            .andExpect( jsonPath( "$composers[*].data[*].middleName", containsInAnyOrder( "Mockito" ) ) )
            .andExpect( jsonPath( "$composers[*].data[*].locale", containsInAnyOrder( "uk-UA" ) ) )
            .andExpect( jsonPath( "$composers[*].locale", containsInAnyOrder( "uk-UA" ) ) )
            .andExpect( jsonPath( "$composers[*].gender", containsInAnyOrder( true ) ) )
            .andExpect( jsonPath( "$composers[*].birthday", notNullValue() ) )
            .andExpect( jsonPath( "$composers[0].deathDate", nullValue() ) )

            .andExpect( jsonPath( "$staffType.id", notNullValue() ) )
            .andExpect( jsonPath( "$staffType.id" ).value( 1 ) )
            .andExpect( jsonPath( "$staffType.code", notNullValue() ) )
            .andExpect( jsonPath( "$staffType.code" ).value( "TAB" ) )
            .andExpect( jsonPath( "$staffType.title", notNullValue() ) )
            .andExpect( jsonPath( "$staffType.title" ).value( "Tabs" ) )
            .andExpect( jsonPath( "$staffType.description", notNullValue() ) )
            .andExpect( jsonPath( "$staffType.description" ).value( "Tabulatures." ) )
            .andExpect( jsonPath( "$style.id", notNullValue() ) )
            .andExpect( jsonPath( "$style.id" ).value( 1 ) )
            .andExpect( jsonPath( "$style.code", notNullValue() ) )
            .andExpect( jsonPath( "$style.code" ).value( "WALTZ" ) )
            .andExpect( jsonPath( "$style.title", notNullValue() ) )
            .andExpect( jsonPath( "$style.title" ).value( "Waltz" ) )
            .andExpect( jsonPath( "$style.description", notNullValue() ) )
            .andExpect( jsonPath( "$style.description" ).value( "Waltz." ) )
            .andExpect( jsonPath( "$locale", notNullValue() ) )
            .andExpect( jsonPath( "$locale" ).value ( "uk-UA" ) )
            .andExpect( jsonPath( "$file", notNullValue() ) )
            .andExpect( jsonPath( "$file" ).value( "ok\\n==============" ) )
            .andExpect( jsonPath( "$creation", notNullValue() ) )
            .andDo(
                document(
                    "staffs-retrieve-success-example",
                    responseFields(
                        fieldWithPath( "id" ).description( "Id of staff." ),
                        fieldWithPath( "composers" ).description( "List of composers." ),
                        fieldWithPath( "staffType.id" ).description( "Id of staff type." ),
                        fieldWithPath( "staffType.code" ).description( "Code of staff type." ),
                        fieldWithPath( "staffType.title" ).description( "Title of staff type." ),
                        fieldWithPath( "staffType.description" ).description( "Description of staff type." ),
                        fieldWithPath( "style.id" ).description( "Id of style type." ),
                        fieldWithPath( "style.code" ).description( "Code of style type." ),
                        fieldWithPath( "style.title" ).description( "Title of style type." ),
                        fieldWithPath( "style.description" ).description( "Description of style type." ),
                        fieldWithPath( "locale" ).description( "Locale of the staff." ),
                        fieldWithPath( "file" ).description( "File of the staff." ),
                        fieldWithPath( "creation" ).description( "Creation date of the staff." )
                    )
                )
            );
    }

    /**
     * Get a staff.
     * Failure.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testFindActionFailure() throws Exception {
        //- Failure -//
        this.mockMvc.perform(
            get( "/music/staffs/{id}", 99999 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
        )
            .andExpect( status().isNotFound() )
            .andDo( document( "staffs-retrieve-failure-example" ) );
    }

    /**
     * Create a staff.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testCreateActionSuccess() throws Exception {
        //- Success -//
        this.mockMvc.perform(
            post( "/music/staffs" )
                .header( "Authorization", this.session.getAuthorizationHeader() )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"songId\": 2," +
                        "\"staffTypeId\": 1," +
                        "\"styleId\": 1," +
                        "\"locale\": \"uk-UA\"" +
                    "}"
                )
        )
            .andExpect( status().isCreated() )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andExpect( jsonPath( "$id", notNullValue() ) )
            .andExpect( jsonPath( "$staffType.id", notNullValue() ) )
            .andExpect( jsonPath( "$staffType.code", notNullValue() ) )
            .andExpect( jsonPath( "$staffType.code" ).value( "TAB" ) )
            .andExpect( jsonPath( "$staffType.title", notNullValue() ) )
            .andExpect( jsonPath( "$staffType.title" ).value( "Tabs" ) )
            .andExpect( jsonPath( "$staffType.description", notNullValue() ) )
            .andExpect( jsonPath( "$staffType.description" ).value( "Tabulatures." ) )
            .andExpect( jsonPath( "$style.id", notNullValue() ) )
            .andExpect( jsonPath( "$style.id" ).value( 1 ) )
            .andExpect( jsonPath( "$style.code", notNullValue() ) )
            .andExpect( jsonPath( "$style.code" ).value( "WALTZ" ) )
            .andExpect( jsonPath( "$style.title", notNullValue() ) )
            .andExpect( jsonPath( "$style.title" ).value( "Waltz" ) )
            .andExpect( jsonPath( "$style.description", notNullValue() ) )
            .andExpect( jsonPath( "$style.description" ).value( "Waltz." ) )
            .andExpect( jsonPath( "$locale", notNullValue() ) )
            .andExpect( jsonPath( "$locale" ).value ( "uk-UA" ) )
//            .andExpect( jsonPath( "$file", notNullValue() ) )
//            .andExpect( jsonPath( "$file" ).value( "ok\\n==============" ) )
//            .andExpect( jsonPath( "$creation", notNullValue() ) )
            .andDo(
                document(
                    "staffs-create-success-example",
                    requestFields(
                        fieldWithPath( "songId" ).description( "Id of song." ),
                        fieldWithPath( "staffTypeId" ).description( "Id of staff." ),
                        fieldWithPath( "styleId" ).description( "Id of style." ),
                        fieldWithPath( "locale" ).description( "Locale of the staff." )
                    )
                )
            );
    }

    /**
     * Create a staff.
     * Failure.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testCreateActionFailure() throws Exception {
        //- Failure -//
        this.mockMvc.perform(
            post( "/music/staffs" )
                .header( "Authorization", this.session.getAuthorizationHeader() )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"songId\": 99999," +
                        "\"staffTypeId\": 1," +
                        "\"styleId\": 1," +
                        "\"locale\": \"uk-UA\"" +
                    "}"
                )
        )
            .andExpect( status().isBadRequest() )
            .andDo( document( "staffs-create-failure-example" ) );
    }

    /**
     * Update a staff.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testUpdateActionSuccess() throws Exception {
        //- Success -//
        this.mockMvc.perform(
            put( "/music/staffs/{id}", 1 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"songId\": 2," +
                        "\"staffTypeId\": 1," +
                        "\"styleId\": 1," +
                        "\"locale\": \"uk-UA\"" +
                    "}"
                )
        )
            .andExpect( status().isOk() )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andExpect( jsonPath( "$id", notNullValue() ) )
            .andExpect( jsonPath( "$id" ).value( 1 ) )
            .andExpect( jsonPath( "$staffType.id", notNullValue() ) )
            .andExpect( jsonPath( "$staffType.id" ).value( 1 ) )
            .andExpect( jsonPath( "$staffType.code", notNullValue() ) )
            .andExpect( jsonPath( "$staffType.code" ).value( "TAB" ) )
            .andExpect( jsonPath( "$staffType.title", notNullValue() ) )
            .andExpect( jsonPath( "$staffType.title" ).value( "Tabs" ) )
            .andExpect( jsonPath( "$staffType.description", notNullValue() ) )
            .andExpect( jsonPath( "$staffType.description" ).value( "Tabulatures." ) )
            .andExpect( jsonPath( "$style.id", notNullValue() ) )
            .andExpect( jsonPath( "$style.id" ).value( 1 ) )
            .andExpect( jsonPath( "$style.code", notNullValue() ) )
            .andExpect( jsonPath( "$style.code" ).value( "WALTZ" ) )
            .andExpect( jsonPath( "$style.title", notNullValue() ) )
            .andExpect( jsonPath( "$style.title" ).value( "Waltz" ) )
            .andExpect( jsonPath( "$style.description", notNullValue() ) )
            .andExpect( jsonPath( "$style.description" ).value( "Waltz." ) )
            .andExpect( jsonPath( "$locale", notNullValue() ) )
            .andExpect( jsonPath( "$locale" ).value ( "uk-UA" ) )
//            .andExpect( jsonPath( "$file", notNullValue() ) )
//            .andExpect( jsonPath( "$file" ).value( "ok\\n==============" ) )
//            .andExpect( jsonPath( "$creation", notNullValue() ) )
            .andDo(
                document(
                    "staffs-update-success-example",
                    requestFields(
                        fieldWithPath( "songId" ).description( "Id of song." ),
                        fieldWithPath( "staffTypeId" ).description( "Id of staff." ),
                        fieldWithPath( "styleId" ).description( "Id of style." ),
                        fieldWithPath( "locale" ).description( "Locale of the staff." )
                    ),
                    responseFields(
                        fieldWithPath( "id" ).description( "Id of staff." ),
                        fieldWithPath( "composers" ).description( "List of composers." ),
                        fieldWithPath( "staffType.id" ).description( "Id of staff type." ),
                        fieldWithPath( "staffType.code" ).description( "Code of staff type." ),
                        fieldWithPath( "staffType.title" ).description( "Title of staff type." ),
                        fieldWithPath( "staffType.description" ).description( "Description of staff type." ),
                        fieldWithPath( "style.id" ).description( "Id of style type." ),
                        fieldWithPath( "style.code" ).description( "Code of style type." ),
                        fieldWithPath( "style.title" ).description( "Title of style type." ),
                        fieldWithPath( "style.description" ).description( "Description of style type." ),
                        fieldWithPath( "locale" ).description( "Locale of the staff." ),
                        fieldWithPath( "file" ).description( "File of the staff." ),
                        fieldWithPath( "creation" ).description( "Creation date of the staff." )
                    )
                )
            );
    }

    /**
     * Update a staff.
     * Failure.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testUpdateActionFailure() throws Exception {
        //- Failure -//
        this.mockMvc.perform(
            put( "/music/staffs/{id}", 99999 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"songId\": 1," +
                        "\"staffTypeId\": 1," +
                        "\"styleId\": 1," +
                        "\"locale\": \"en-US\"" +
                    "}"
                )
        )
            .andExpect( status().isBadRequest() )
            .andDo( document( "staffs-update-failure-example" ) );
    }

    /**
     * Delete a staff.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testDeleteActionSuccess() throws Exception {
        //- Failure -//
        this.mockMvc.perform(
            delete( "/music/staffs/{id}", 1 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
        )
            .andExpect( status().isOk() )
            .andDo( document( "staffs-delete-success-example" ) );
    }

    /**
     * Delete a staff.
     * Failure.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testDeleteActionFailure() throws Exception {
        //- Failure -//
        this.mockMvc.perform(
            delete( "/music/staffs/{id}", 99999 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
        )
            .andExpect( status().isNotFound() )
            .andDo( document( "staffs-delete-failure-example" ) );
    }
}

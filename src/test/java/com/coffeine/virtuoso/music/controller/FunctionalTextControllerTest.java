/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 5/8/16 11:16 AM
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
 * Tests for TextController.
 *
 * @version 1.0
 */
public class FunctionalTextControllerTest extends AbstractRestControllerTest {

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
     * Get list of texts.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testListAction() throws Exception {
        //- Success -//
        this.mockMvc.perform(
            get( "/music/texts?page={page}&limit={limit}", 1, 10 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
        )
            .andExpect( status().isOk() )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andExpect( jsonPath( "$", hasSize( 1 ) ) )
            .andExpect( jsonPath( "$[*].id", notNullValue() ) )
            .andExpect( jsonPath( "$[*].id", containsInAnyOrder( 1 ) ) )
            .andExpect( jsonPath( "$[*].locale", notNullValue() ) )
            .andExpect( jsonPath( "$[*].locale", containsInAnyOrder( "uk-UA" ) ) )
            .andExpect( jsonPath( "$[*].lyrics", notNullValue() ) )
            .andExpect( jsonPath( "$[*].lyrics", containsInAnyOrder( "Rose\\n==============" ) ) )
            .andExpect( jsonPath( "$[*].creation", notNullValue() ) )
            .andDo(
                document(
                    "texts-list-example",
                    responseFields(
                        fieldWithPath( "[].id" ).description( "Id of text." ),
                        fieldWithPath( "[].locale" ).description( "Locale of the text." ),
                        fieldWithPath( "[].lyrics" ).description( "Lyrics of the text." ),
                        fieldWithPath( "[].creation" ).description( "Creation date of the text." )
                    )
                )
            );
    }

    /**
     * Get a text.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testFindActionSuccess() throws Exception {
        //- Success -//
        this.mockMvc.perform(
            get( "/music/texts/{id}", 1 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
        )
            .andExpect( status().isOk() )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andExpect( jsonPath( "$id", notNullValue() ) )
            .andExpect( jsonPath( "$locale", notNullValue() ) )
            .andExpect( jsonPath( "$locale" ).value( "uk-UA" ) )
            .andExpect( jsonPath( "lyrics", notNullValue() ) )
            .andExpect( jsonPath( "lyrics" ).value( "Rose\\n==============" ) )
            .andExpect( jsonPath( "$creation", notNullValue() ) )
            .andDo(
                document(
                    "texts-retrieve-success-example",
                    responseFields(
                        fieldWithPath( "id" ).description( "Id of text." ),
                        fieldWithPath( "locale" ).description( "Locale of the text." ),
                        fieldWithPath( "lyrics" ).description( "Lyrics of the text." ),
                        fieldWithPath( "creation" ).description( "Creation date of the text." )
                    )
                )
            );
    }

    /**
     * Get a text.
     * Failure.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testFindActionFailure() throws Exception {
        //- Failure -//
        this.mockMvc.perform(
            get( "/music/texts/{id}", 99999 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
        )
            .andExpect( status().isNotFound() )
            .andDo( document( "texts-retrieve-failure-example" ) );
    }

    /**
     * Create a text.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testCreateActionSuccess() throws Exception {
        //- Success -//
        this.mockMvc.perform(
            post( "/music/texts" )
                .header( "Authorization", this.session.getAuthorizationHeader() )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"songId\": 2," +
                        "\"locale\": \"en-US\"" +
                    "}"
                )
        )//FIXME
            .andExpect( status().isCreated() )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andExpect( jsonPath( "$id", notNullValue() ) )
            .andExpect( jsonPath( "$locale", notNullValue() ) )
            .andExpect( jsonPath( "$locale" ).value( "en-US" ) )
//            .andExpect( jsonPath( "$lyrics", notNullValue() ) )
//            .andExpect( jsonPath( "$lyrics" ).value( "Rose\\n==============" ) )
//            .andExpect( jsonPath( "$creation", notNullValue() ) )
            .andDo(
                document(
                    "texts-create-success-example",
                    requestFields(
                        fieldWithPath( "songId" ).description( "Id of song." ),
                        fieldWithPath( "locale" ).description( "Locale of the video." )
                    )
                )
            );
    }

    /**
     * Create a text.
     * Failure.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testCreateActionFailure() throws Exception {
        //- Failure -//
        this.mockMvc.perform(
            post( "/music/texts" )
                .header( "Authorization", this.session.getAuthorizationHeader() )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"songId\": 1," +
                        "\"locale\": \"uk-UA\"" +
                    "}"
                )
        )
//            .andExpect( status().isConflict() )
            .andDo( document( "texts-create-failure-example" ) );
    }

    /**
     * Create a text.
     * Failure.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testCreateActionFailureInput() throws Exception {
        //- Failure -//
        this.mockMvc.perform(
            post( "/music/texts" )
                .header( "Authorization", this.session.getAuthorizationHeader() )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"songId\": 99999," +
                        "\"locale\": \"uk-UA\"" +
                    "}"
                )
        )
            .andExpect( status().isNotFound() )
            .andDo( document( "texts-create-failure-input-example" ) );
    }

    /**
     * Update a text.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testUpdateActionSuccess() throws Exception {
        //- Success -//
        this.mockMvc.perform(
            put( "/music/texts/{id}", 1 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"songId\": 1," +
                        "\"locale\": \"uk\"" +
                    "}"
                )
        )
            .andExpect( status().isOk() )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andExpect( jsonPath( "$id", notNullValue() ) )
            .andExpect( jsonPath( "$locale", notNullValue() ) )
            .andExpect( jsonPath( "$locale" ).value( "uk" ) )
            .andExpect( jsonPath( "lyrics", notNullValue() ) )
            .andExpect( jsonPath( "lyrics" ).value( "Rose\\n==============" ) )
            .andExpect( jsonPath( "$creation", notNullValue() ) )
            .andDo( document( "texts-update-success-example" ) );
    }

    /**
     * Update a text.
     * Failure.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testUpdateActionFailure() throws Exception {
        //- Failure -//
        this.mockMvc.perform(
            put( "/music/texts/{id}", 99999 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"songId\": 1," +
                        "\"locale\": \"uk-UA\"" +
                    "}"
                )
        )
            .andExpect( status().isNotFound() )
            .andDo( document( "texts-update-failure-example" ) );
    }

    /**
     * Delete a text.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testDeleteActionSuccess() throws Exception {
        //- Failure -//
        this.mockMvc.perform(
            delete( "/music/texts/{id}", 1 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
        )
            .andExpect( status().isOk() )
            .andDo( document( "texts-delete-success-example" ) );
    }

    /**
     * Delete a text.
     * Failure.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testDeleteActionFailure() throws Exception {
        //- Failure -//
        this.mockMvc.perform(
            delete( "/music/texts/{id}", 99999 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
        )
            .andExpect( status().isNotFound() )
            .andDo( document( "texts-delete-failure-example" ) );
    }
}

/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 5/8/16 11:16 AM
 */

package com.thecoffeine.virtuoso.music.controller;

import com.thecoffeine.virtuoso.module.controller.AbstractRestControllerTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for TextController.
 *
 * @version 1.0
 */
public class FunctionalLyricsControllerTest extends AbstractRestControllerTest {

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
     * Get list of lyrics.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testListAction() throws Exception {
        //- Success -//
        this.mockMvc.perform(
            get( "/music/lyrics?page={page}&limit={limit}", 1, 10 )
        )
            .andExpect( status().isOk() )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andExpect( jsonPath( "$", hasSize( 1 ) ) )
            .andExpect( jsonPath( "$[*].id", notNullValue() ) )
            .andExpect( jsonPath( "$[*].id", containsInAnyOrder( 1 ) ) )
            //- Poet -//
            .andExpect( jsonPath( "$[*].poets[*].id", containsInAnyOrder( 1 ) ) )
            .andExpect( jsonPath( "$[*].poets[*].data[*].firstName", containsInAnyOrder( "Test" ) ) )
            .andExpect( jsonPath( "$[*].poets[*].data[*].lastName", containsInAnyOrder( "Unit" ) ) )
            .andExpect( jsonPath( "$[*].poets[*].data[*].middleName", containsInAnyOrder( "Mockito" ) ) )
            .andExpect( jsonPath( "$[*].poets[*].data[*].locale", containsInAnyOrder( "uk-UA" ) ) )
            .andExpect( jsonPath( "$[*].poets[*].locale", containsInAnyOrder( "uk-UA" ) ) )
            .andExpect( jsonPath( "$[*].poets[*].gender", containsInAnyOrder( true ) ) )
            .andExpect( jsonPath( "$[*].poets[*].birthday", notNullValue() ) )
            .andExpect( jsonPath( "$[*].poets[0].deathDate", nullValue() ) )
            .andExpect( jsonPath( "$[*].locale", notNullValue() ) )
            .andExpect( jsonPath( "$[*].locale", containsInAnyOrder( "uk-UA" ) ) )
            .andExpect( jsonPath( "$[*].lyrics", notNullValue() ) )
            .andExpect( jsonPath( "$[*].lyrics", containsInAnyOrder( "Rose\\n==============" ) ) )
            .andExpect( jsonPath( "$[*].creation", notNullValue() ) )
            .andDo(
                document(
                    "lyrics-list-example",
                    responseFields(
                        fieldWithPath( "[].id" ).description( "Id of text." ),
                        fieldWithPath( "[].poets" ).description( "List of poets." ),
                        fieldWithPath( "[].locale" ).description( "Locale of the text." ),
                        fieldWithPath( "[].lyrics" ).description( "Lyrics of the text." ),
                        fieldWithPath( "[].creation" ).description( "Creation date of the text." )
                    )
                )
            );
    }

    /**
     * Get a lyrics.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testFindActionSuccess() throws Exception {
        //- Success -//
        this.mockMvc.perform(
            get( "/music/lyrics/{id}", 1 )
        )
            .andExpect( status().isOk() )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andExpect( jsonPath( "$id", notNullValue() ) )
            //- Poet -//
            .andExpect( jsonPath( "$poets[*].id", containsInAnyOrder( 1 ) ) )
            .andExpect( jsonPath( "$poets[*].data[*].firstName", containsInAnyOrder( "Test" ) ) )
            .andExpect( jsonPath( "$poets[*].data[*].lastName", containsInAnyOrder( "Unit" ) ) )
            .andExpect( jsonPath( "$poets[*].data[*].middleName", containsInAnyOrder( "Mockito" ) ) )
            .andExpect( jsonPath( "$poets[*].data[*].locale", containsInAnyOrder( "uk-UA" ) ) )
            .andExpect( jsonPath( "$poets[*].locale", containsInAnyOrder( "uk-UA" ) ) )
            .andExpect( jsonPath( "$poets[*].gender", containsInAnyOrder( true ) ) )
            .andExpect( jsonPath( "$poets[*].birthday", notNullValue() ) )
            .andExpect( jsonPath( "$poets[0].deathDate", nullValue() ) )
            .andExpect( jsonPath( "$locale", notNullValue() ) )
            .andExpect( jsonPath( "$locale" ).value( "uk-UA" ) )
            .andExpect( jsonPath( "lyrics", notNullValue() ) )
            .andExpect( jsonPath( "lyrics" ).value( "Rose\\n==============" ) )
            .andExpect( jsonPath( "$creation", notNullValue() ) )
            .andDo(
                document(
                    "lyrics-retrieve-success-example",
                    responseFields(
                        fieldWithPath( "id" ).description( "Id of text." ),
                        fieldWithPath( "poets" ).description( "List of poets." ),
                        fieldWithPath( "locale" ).description( "Locale of the text." ),
                        fieldWithPath( "lyrics" ).description( "Lyrics of the text." ),
                        fieldWithPath( "creation" ).description( "Creation date of the text." )
                    )
                )
            );
    }

    /**
     * Get a lyrics.
     * Failure.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testFindActionFailure() throws Exception {
        //- Failure -//
        this.mockMvc.perform(
            get( "/music/lyrics/{id}", 99999 )
        )
            .andExpect( status().isNotFound() )
            .andDo( document( "lyrics-retrieve-failure-example" ) );
    }

    /**
     * Create a lyrics.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testCreateActionSuccess() throws Exception {
        //- Success -//
        this.mockMvc.perform(
            post( "/music/lyrics" )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"poetIds\": [ 1 ]," +
                        "\"songId\": 2," +
                        "\"locale\": \"en-US\"," +
                        "\"content\": \"Lyrics content.Lyrics content.Lyrics content.Lyrics content.Lyrics content.Lyrics content.Lyrics content.Lyrics content.\"" +
                    "}"
                )
        )//FIXME
            .andDo( print() )
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
                    "lyrics-create-success-example",
                    requestFields(
                        fieldWithPath( "songId" ).description( "Id of lyrics." ),
                        fieldWithPath( "poetIds" ).description( "Poets ids of lyrics." ),
                        fieldWithPath( "locale" ).description( "Locale of the Lyrics." ),
                        fieldWithPath( "content" ).description( "Lyrics content." )
                    )
                )
            );
    }

    /**
     * Create a lyrics.
     * Failure.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testCreateActionFailure() throws Exception {
        //- Failure -//
        this.mockMvc.perform(
            post( "/music/lyrics" )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"songId\": 1," +
                        "\"locale\": \"uk-UA\"" +
                    "}"
                )
        )
//            .andExpect( status().isConflict() )
            .andDo( document( "lyrics-create-failure-example" ) );
    }

    /**
     * Create a lyrics.
     * Failure.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testCreateActionFailureInput() throws Exception {
        //- Failure -//
        this.mockMvc.perform(
            post( "/music/lyrics" )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"poetIds\": [ 1 ]," +
                        "\"songId\": 99999," +
                        "\"locale\": \"uk-UA\"," +
                        "\"content\": \"Lyrics content.Lyrics content.Lyrics content.Lyrics content.Lyrics content.Lyrics content.Lyrics content.Lyrics content.\"" +
                    "}"
                )
        ).andDo( print() )
            .andExpect( status().isNotFound() )
            .andDo( document( "lyrics-create-failure-input-example" ) );
    }

    /**
     * Update a lyrics.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testUpdateActionSuccess() throws Exception {
        //- Success -//
        this.mockMvc.perform(
            put( "/music/lyrics/{id}", 1 )
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
            .andDo( document( "lyrics-update-success-example" ) );
    }

    /**
     * Update a lyrics.
     * Failure.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testUpdateActionFailure() throws Exception {
        //- Failure -//
        this.mockMvc.perform(
            put( "/music/lyrics/{id}", 99999 )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"songId\": 1," +
                        "\"locale\": \"uk-UA\"" +
                    "}"
                )
        )
            .andExpect( status().isNotFound() )
            .andDo( document( "lyrics-update-failure-example" ) );
    }

    /**
     * Delete a lyrics.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testDeleteActionSuccess() throws Exception {
        //- Failure -//
        this.mockMvc.perform(
            delete( "/music/lyrics/{id}", 1 )
        )
            .andExpect( status().isOk() )
            .andDo( document( "lyrics-delete-success-example" ) );
    }

    /**
     * Delete a lyrics.
     * Failure.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testDeleteActionFailure() throws Exception {
        //- Failure -//
        this.mockMvc.perform(
            delete( "/music/lyrics/{id}", 99999 )
        )
            .andExpect( status().isNotFound() )
            .andDo( document( "lyrics-delete-failure-example" ) );
    }
}

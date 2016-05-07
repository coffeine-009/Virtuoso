/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 5/7/16 6:52 PM
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
 * Tests for VideoController.
 *
 * @version 1.0
 */
public class FunctionalVideoControllerTest extends AbstractRestControllerTest {

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
     * Get list of videos.
     * Success.
     *
     * @throws Exception
     */
    @Test
    public void testListAction() throws Exception {
        //- Success -//
        this.mockMvc.perform(
            get( "/music/videos?page={page}&limit={limit}", 1, 10 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
        )
            .andExpect( status().isOk() )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andExpect( jsonPath( "$", hasSize( 1 ) ) )
            .andExpect( jsonPath( "$[*].id", notNullValue() ) )
            .andExpect( jsonPath( "$[*].id", containsInAnyOrder( 1 ) ) )
            .andExpect( jsonPath( "$[*].videoType.id", notNullValue() ) )
            .andExpect( jsonPath( "$[*].videoType.id", containsInAnyOrder( 1 ) ) )
            .andExpect( jsonPath( "$[*].videoType.code", notNullValue() ) )
            .andExpect( jsonPath( "$[*].videoType.code", containsInAnyOrder( "YOUTUBE" ) ) )
            .andExpect( jsonPath( "$[*].videoType.title", notNullValue() ) )
            .andExpect( jsonPath( "$[*].videoType.title", containsInAnyOrder( "Youtube" ) ) )
            .andExpect( jsonPath( "$[*].videoType.description", notNullValue() ) )
            .andExpect( jsonPath( "$[*].videoType.description", containsInAnyOrder( "Youtube." ) ) )
            .andExpect( jsonPath( "$[*].locale", notNullValue() ) )
            .andExpect( jsonPath( "$[*].locale", containsInAnyOrder( "uk-UA" ) ) )
            .andExpect( jsonPath( "$[*].title", notNullValue() ) )
            .andExpect( jsonPath( "$[*].title", containsInAnyOrder( "Rose" ) ) )
            .andExpect( jsonPath( "$[*].description", notNullValue() ) )
            .andExpect( jsonPath( "$[*].description", containsInAnyOrder( "Rose. Ukrainian song." ) ) )
            .andExpect( jsonPath( "$[*].fileName", notNullValue() ) )
            .andExpect( jsonPath( "$[*].fileName", containsInAnyOrder( "rose.mp4" ) ) )
            .andExpect( jsonPath( "$[*].creation", notNullValue() ) )
            .andDo(
                document(
                    "videos-list-example",
                    responseFields(
                        fieldWithPath( "[].id" ).description( "Id of video." ),
                        fieldWithPath( "[].videoType.id" ).description( "Id of video." ),
                        fieldWithPath( "[].videoType.code" ).description( "Code of video." ),
                        fieldWithPath( "[].videoType.title" ).description( "Title of video." ),
                        fieldWithPath( "[].videoType.description" ).description( "Description of video." ),
                        fieldWithPath( "[].locale" ).description( "Locale of the video." ),
                        fieldWithPath( "[].title" ).description( "Title of the video." ),
                        fieldWithPath( "[].description" ).description( "Description of the video." ),
                        fieldWithPath( "[].fileName" ).description( "File of the video." ),
                        fieldWithPath( "[].creation" ).description( "Creation date of the video." )
                    )
                )
            );
    }

    /**
     * Get a video.
     * Success.
     *
     * @throws Exception
     */
    @Test
    public void testFindActionSuccess() throws Exception {
        //- Success -//
        this.mockMvc.perform(
            get( "/music/videos/{id}", 1 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
        )
            .andExpect( status().isOk() )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andExpect( jsonPath( "$id", notNullValue() ) )
            .andExpect( jsonPath( "$videoType.id", notNullValue() ) )
            .andExpect( jsonPath( "$videoType.code", notNullValue() ) )
            .andExpect( jsonPath( "$videoType.code" ).value( "YOUTUBE" ) )
            .andExpect( jsonPath( "$videoType.title", notNullValue() ) )
            .andExpect( jsonPath( "$videoType.title" ).value( "Youtube" ) )
            .andExpect( jsonPath( "$videoType.description", notNullValue() ) )
            .andExpect( jsonPath( "$videoType.description" ).value( "Youtube." ) )
            .andExpect( jsonPath( "$locale", notNullValue() ) )
            .andExpect( jsonPath( "$locale" ).value( "uk-UA" ) )
            .andExpect( jsonPath( "$title", notNullValue() ) )
            .andExpect( jsonPath( "$title" ).value( "Rose" ) )
            .andExpect( jsonPath( "$description", notNullValue() ) )
            .andExpect( jsonPath( "$description" ).value( "Rose. Ukrainian song." ) )
            .andExpect( jsonPath( "$fileName", notNullValue() ) )
            .andExpect( jsonPath( "$fileName" ).value( "rose.mp4" ) )
            .andExpect( jsonPath( "$creation", notNullValue() ) )
            .andDo(
                document(
                    "videos-retrieve-success-example",
                    responseFields(
                        fieldWithPath( "id" ).description( "Id of video." ),
                        fieldWithPath( "videoType.id" ).description( "Id of video." ),
                        fieldWithPath( "videoType.code" ).description( "Code of video." ),
                        fieldWithPath( "videoType.title" ).description( "Title of video." ),
                        fieldWithPath( "videoType.description" ).description( "Description of video." ),
                        fieldWithPath( "locale" ).description( "Locale of the video." ),
                        fieldWithPath( "title" ).description( "Title of the video." ),
                        fieldWithPath( "description" ).description( "Description of the video." ),
                        fieldWithPath( "fileName" ).description( "File of the video." ),
                        fieldWithPath( "creation" ).description( "Creation date of the video." )
                    )
                )
            );
    }

    /**
     * Get a video.
     * Failure.
     *
     * @throws Exception
     */
    @Test
    public void testFindActionFailure() throws Exception {
        //- Failure -//
        this.mockMvc.perform(
            get( "/music/video/{id}", 99999 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
        )
            .andExpect( status().isNotFound() )
            .andDo( document( "videos-retrieve-failure-example" ) );
    }

    /**
     * Create a video.
     * Success.
     *
     * @throws Exception
     */
    @Test
    public void testCreateActionSuccess() throws Exception {
        //- Success -//
        this.mockMvc.perform(
            post( "/music/videos" )
                .header( "Authorization", this.session.getAuthorizationHeader() )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"videoTypeId\": 1," +
                        "\"songId\": 2," +
                        "\"locale\": \"en-US\"," +
                        "\"title\": \"Red rose\"," +
                        "\"description\": \"Ukrainian song.\"," +
                        "\"fileName\": \"red-rose.mp4\"" +
                    "}"
                )
        )
            .andExpect( status().isCreated() )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andExpect( jsonPath( "$id", notNullValue() ) )
            .andExpect( jsonPath( "$videoType.id", notNullValue() ) )
            .andExpect( jsonPath( "$videoType.code", notNullValue() ) )
            .andExpect( jsonPath( "$videoType.title", notNullValue() ) )
            .andExpect( jsonPath( "$videoType.description", notNullValue() ) )
            .andExpect( jsonPath( "$locale", notNullValue() ) )
            .andExpect( jsonPath( "$locale" ).value( "en-US" ) )
            .andExpect( jsonPath( "$title", notNullValue() ) )
            .andExpect( jsonPath( "$title" ).value( "Red rose" ) )
            .andExpect( jsonPath( "$description", notNullValue() ) )
            .andExpect( jsonPath( "$description" ).value( "Ukrainian song." ) )
            .andExpect( jsonPath( "$fileName", notNullValue() ) )
            .andExpect( jsonPath( "$fileName" ).value( "red-rose.mp4" ) )
//            .andExpect( jsonPath( "$creation", notNullValue() ) )
            .andDo(
                document(
                    "videos-create-success-example",
                    requestFields(
                        fieldWithPath( "videoTypeId" ).description( "Id of video type." ),
                        fieldWithPath( "songId" ).description( "Id of song." ),
                        fieldWithPath( "locale" ).description( "Locale of the video." ),
                        fieldWithPath( "title" ).description( "Title of the video." ),
                        fieldWithPath( "description" ).description( "Description of the video." ),
                        fieldWithPath( "fileName" ).description( "File of the video." )
                    )
                )
            );
    }

    /**
     * Create a video.
     * Failure.
     *
     * @throws Exception
     */
    @Test
    public void testCreateActionFailure() throws Exception {
        //- Failure -//
        this.mockMvc.perform(
            post( "/music/videos" )
                .header( "Authorization", this.session.getAuthorizationHeader() )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"videoTypeId\": 1," +
                        "\"songId\": 1," +
                        "\"locale\": \"uk-UA\"," +
                        "\"title\": \"Rose\"," +
                        "\"description\": \"Rose. Ukrainian song.\"," +
                        "\"fileName\": \"rose.mp4\"" +
                    "}"
                )
        )
//            .andExpect( status().isConflict() )
            .andDo( document( "videos-create-failure-example" ) );
    }

    /**
     * Create a video.
     * Failure.
     *
     * @throws Exception
     */
    @Test
    public void testCreateActionFailureInput() throws Exception {
        //- Failure -//
        this.mockMvc.perform(
            post( "/music/videos" )
                .header( "Authorization", this.session.getAuthorizationHeader() )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"videoTypeId\": 99999," +
                        "\"songId\": 99999," +
                        "\"locale\": \"uk-UA\"," +
                        "\"title\": \"Rose\"," +
                        "\"description\": \"Rose. Ukrainian song.\"," +
                        "\"fileName\": \"rose.mp4\"" +
                    "}"
                )
        )
            .andExpect( status().isNotFound() )
            .andDo( document( "videos-create-failure-input-example" ) );
    }

    /**
     * Update a video.
     * Success.
     *
     * @throws Exception
     */
    @Test
    public void testUpdateActionSuccess() throws Exception {
        //- Success -//
        this.mockMvc.perform(
            put( "/music/videos/{id}", 1 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"videoTypeId\": 1," +
                        "\"songId\": 1," +
                        "\"locale\": \"en\"," +
                        "\"title\": \"Rose\"," +
                        "\"description\": \"Rose. Ukrainian song.\"," +
                        "\"fileName\": \"rose.mp4\"" +
                    "}"
                )
        )
            .andExpect( status().isOk() )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andExpect( jsonPath( "$id", notNullValue() ) )
            .andExpect( jsonPath( "$videoType", notNullValue() ) )
            .andExpect( jsonPath( "$locale", notNullValue() ) )
            .andExpect( jsonPath( "$locale" ).value( "en" ) )
            .andExpect( jsonPath( "$title", notNullValue() ) )
            .andExpect( jsonPath( "$title" ).value( "Rose" ) )
            .andExpect( jsonPath( "$description", notNullValue() ) )
            .andExpect( jsonPath( "$description" ).value( "Rose. Ukrainian song." ) )
            .andExpect( jsonPath( "$fileName", notNullValue() ) )
            .andExpect( jsonPath( "$fileName" ).value( "rose.mp4" ) )
//            .andExpect( jsonPath( "$creation", notNullValue() ) )
            .andDo( document( "videos-update-success-example" ) );
    }

    /**
     * Update a video.
     * Failure.
     *
     * @throws Exception
     */
    @Test
    public void testUpdateActionFailure() throws Exception {
        //- Failure -//
        this.mockMvc.perform(
            put( "/music/videos/{id}", 99999 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"videoTypeId\": 1," +
                        "\"songId\": 1," +
                        "\"locale\": \"en-US\"," +
                        "\"title\": \"Rose\"," +
                        "\"description\": \"Rose. Ukrainian song.\"," +
                        "\"fileName\": \"rose.mp4\"" +
                    "}"
                )
        )
            .andExpect( status().isConflict() )
            .andDo( document( "videos-update-failure-example" ) );
    }

    /**
     * Delete a video.
     * Success.
     *
     * @throws Exception
     */
    @Test
    public void testDeleteActionSuccess() throws Exception {
        //- Failure -//
        this.mockMvc.perform(
            delete( "/music/videos/{id}", 1 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
        )
            .andExpect( status().isOk() )
            .andDo( document( "videos-delete-success-example" ) );
    }

    /**
     * Delete a video.
     * Failure.
     *
     * @throws Exception
     */
    @Test
    public void testDeleteActionFailure() throws Exception {
        //- Failure -//
        this.mockMvc.perform(
            delete( "/music/videos/{id}", 99999 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
        )
            .andExpect( status().isNotFound() )
            .andDo( document( "videos-delete-failure-example" ) );
    }
}

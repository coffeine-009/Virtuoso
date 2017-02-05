/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 5/7/16 2:06 PM
 */

package com.thecoffeine.virtuoso.music.controller;

import com.thecoffeine.virtuoso.module.controller.AbstractRestControllerTest;
import com.thecoffeine.virtuoso.module.util.TypeHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
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
 * Tests for video types.
 */
public class FunctionalVideoTypeControllerTest extends AbstractRestControllerTest {

    /// *** Methods     *** //
    @Before
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


    //- SECTION :: TEST -//
    /**
     * Test for list action.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testListAction() throws Exception {

        final ResultActions responseActions = this.mockMvc.perform(
            get( "/music/videos/types?page={page}&limit={limit}", 1, 10 )
                .contentType( MediaType.APPLICATION_JSON )
        );

        responseActions
            .andExpect( status().isOk() )
            .andDo(
                document(
                    "video-types-list-example",
                    responseFields(
                        fieldWithPath( "[].id" ).description( "Id of video type." ),
                        fieldWithPath( "[].code" ).description( "Code of video type." ),
                        fieldWithPath( "[].title" ).description( "Title of video type." ),
                        fieldWithPath( "[].description" ).description( "Description of video type." )
                    )
                )
            );

        TypeHelper.check( responseActions, "YOUTUBE", "Youtube", "Youtube." );
    }

    /**
     * Test for create action.
     * Success
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testCreateActionSuccess() throws Exception {

        this.mockMvc.perform(
            post( "/music/videos/types" )
                .contentType( MediaType.APPLICATION_JSON )
            .content(
                "{" +
                    "\"code\": \"VIMEO\"," +
                    "\"title\": \"Vimeo\"," +
                    "\"description\": \"Vimeo.\"" +
                "}"
            )
        )
            .andExpect( status().isCreated() )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andExpect( jsonPath( "$id", notNullValue() ) )
            .andExpect( jsonPath( "$code", notNullValue() ) )
            .andExpect( jsonPath( "$code" ).value ( "VIMEO" ) )
            .andExpect( jsonPath( "$title", notNullValue() ) )
            .andExpect( jsonPath( "$title" ).value( "Vimeo" ) )
            .andExpect( jsonPath( "$description", notNullValue() ) )
            .andExpect( jsonPath( "$description" ).value( "Vimeo." ) )
            .andDo(
                document(
                    "video-types-create-success-example",
                    requestFields(
                        fieldWithPath( "code" ).description( "Code of video type." ),
                        fieldWithPath( "title" ).description( "Title of video type." ),
                        fieldWithPath( "description" ).description( "Description of video type." )
                    ),
                    responseFields(
                        fieldWithPath( "id" ).description( "Id of video type." ),
                        fieldWithPath( "code" ).description( "Code of video type." ),
                        fieldWithPath( "title" ).description( "Title of video type." ),
                        fieldWithPath( "description" ).description( "Description of video type." )
                    )
                )
            );
    }

    /**
     * Test for create action.
     * failure
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testCreateActionFailure() throws Exception {

        this.mockMvc.perform(
            post( "/music/videos/types" )
                .contentType( MediaType.APPLICATION_JSON )
            .content(
                "{" +
                    "\"code\": \"YOUTUBE\"," +
                    "\"title\": \"Youtube\"," +
                    "\"description\": \"Youtube.\"" +
                "}"
            )
        )
            .andExpect( status().isCreated() )
            .andDo(
                document(
                    "video-types-create-failure-example",
                    requestFields(
                        fieldWithPath( "code" ).description( "Code of video type." ),
                        fieldWithPath( "title" ).description( "Title of video type." ),
                        fieldWithPath( "description" ).description( "Description of video type." )
                    )
                )
            );
    }

    /**
     * Test for find action.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testFindActionSuccess() throws Exception {

        this.mockMvc.perform(
            get( "/music/videos/types/{id}", 1 )
                .contentType( MediaType.APPLICATION_JSON )
        )
            .andExpect( status().isOk() )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andExpect( jsonPath( "$id", notNullValue() ) )
            .andExpect( jsonPath( "$code", notNullValue() ) )
            .andExpect( jsonPath( "$code" ).value( "YOUTUBE" ) )
            .andExpect( jsonPath( "$title", notNullValue() ) )
            .andExpect( jsonPath( "$title" ).value( "Youtube" ) )
            .andExpect( jsonPath( "$description", notNullValue() ) )
            .andExpect( jsonPath( "$description" ).value( "Youtube." ) )
            .andDo(
                document(
                    "video-types-retrieve-success-example",
                    responseFields(
                        fieldWithPath( "id" ).description( "Id of video type." ),
                        fieldWithPath( "code" ).description( "Code of video type." ),
                        fieldWithPath( "title" ).description( "Title of video type." ),
                        fieldWithPath( "description" ).description( "Description of video type." )
                    )
                )
            );
    }

    /**
     * Test for find action.
     * Failure.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testFindActionFailure() throws Exception {

        this.mockMvc.perform(
            get( "/music/videos/types/{id}", 99999 )
                .contentType( MediaType.APPLICATION_JSON )
        )
            .andExpect( status().isNotFound() )
            .andDo( document( "video-types-retrieve-failure-example" ) );
    }

    /**
     * Test for update action.
     * Success
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testUpdateActionSuccess() throws Exception {

        this.mockMvc.perform(
            put( "/music/videos/types/{id}", 1 )
                .contentType( MediaType.APPLICATION_JSON )
                .content(
                    "{" +
                        "\"code\": \"FILE\"," +
                        "\"title\": \"File\"," +
                        "\"description\": \"File.\"" +
                    "}"
                )
        )
            .andExpect( status().isOk() )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andExpect( jsonPath( "$id", notNullValue() ) )
            .andExpect( jsonPath( "$code", notNullValue() ) )
            .andExpect( jsonPath( "$code" ).value ( "FILE" ) )
            .andExpect( jsonPath( "$title", notNullValue() ) )
            .andExpect( jsonPath( "$title" ).value( "File" ) )
            .andExpect( jsonPath( "$description", notNullValue() ) )
            .andExpect( jsonPath( "$description" ).value( "File." ) )
            .andDo(
                document(
                    "video-types-update-success-example",
                    requestFields(
                        fieldWithPath( "code" ).description( "Code of video type." ),
                        fieldWithPath( "title" ).description( "Title of video type." ),
                        fieldWithPath( "description" ).description( "Description of video type." )
                    ),
                    responseFields(
                        fieldWithPath( "id" ).description( "Id of video type." ),
                        fieldWithPath( "code" ).description( "Code of video type." ),
                        fieldWithPath( "title" ).description( "Title of video type." ),
                        fieldWithPath( "description" ).description( "Description of video type." )
                    )
                )
            );
    }

    /**
     * Test for update action.
     * failure
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testUpdateActionFailure() throws Exception {

        this.mockMvc.perform(
            put( "/music/videos/types/{id}", 9999 )
                .contentType( MediaType.APPLICATION_JSON )
                .content(
                    "{" +
                        "\"code\": \"YOUTUBE\"," +
                        "\"title\": \"Youtube\"," +
                        "\"description\": \"Youtube.\"" +
                    "}"
                )
        )
            .andExpect( status().isNotFound() )
            .andDo(
                document(
                    "video-types-update-failure-example",
                    requestFields(
                        fieldWithPath( "code" ).description( "Code of video type." ),
                        fieldWithPath( "title" ).description( "Title of video type." ),
                        fieldWithPath( "description" ).description( "Description of video type." )
                    )
                )
            );
    }



    /**
     * Test for delete action.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testDeleteActionSuccess() throws Exception {

        this.mockMvc.perform(
            delete( "/music/videos/types/{id}", 1 )
        )
            .andExpect( status().isOk() )
            .andDo( document( "video-types-delete-success-example" ) );
    }

    /**
     * Test for delete action.
     * Failure.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testDeleteActionFailure() throws Exception {

        this.mockMvc.perform(
            delete( "/music/videos/types/{id}", 99999 )
        )
            .andExpect( status().isNotFound() )
            .andDo( document( "video-types-delete-failure-example" ) );
    }
}

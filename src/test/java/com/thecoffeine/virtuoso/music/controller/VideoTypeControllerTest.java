/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.thecoffeine.virtuoso.music.controller;

import com.thecoffeine.virtuoso.module.controller.AbstractRestControllerTest;
import com.thecoffeine.virtuoso.music.model.entity.VideoType;
import com.thecoffeine.virtuoso.music.model.persistence.mock.VideoTypeMock;
import com.thecoffeine.virtuoso.music.model.service.VideoTypeService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for VideoType Controller.
 * @see VideoTypeController
 *
 * @version 1.0
 */
public class VideoTypeControllerTest extends AbstractRestControllerTest {

    @Mock
    protected VideoTypeService videoTypeService;

    @InjectMocks
    private VideoTypeController videoTypeController;


    /// *** Methods     *** //
    @Before
    public void tearUp() {
        super.tearUp();

        //- Set up application -//
        this.mockMvc = MockMvcBuilders.standaloneSetup( this.videoTypeController ).build();
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

        when( this.videoTypeService.findAll( anyInt(), anyInt() ) ).thenReturn(
            VideoTypeMock.findAll()
        );

        this.mockMvc.perform(
            get( "/music/videos/types?page={page}&limit={limit}", 1, 10 )
                .contentType( MediaType.APPLICATION_JSON )
        )
            .andExpect( status().isOk() );
    }

    /**
     * Test for create action.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testCreateActionSuccess() throws Exception {

        when( this.videoTypeService.create( any( VideoType.class )) ).thenReturn(
            VideoTypeMock.find()
        );

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
            .andExpect( status().isCreated() );
    }

    /**
     * Test for create action.
     * Failure
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testCreateActionFailure() throws Exception {
        //- Mock -//
        doThrow( DataIntegrityViolationException.class ).when(
            this.videoTypeService
        ).create( any( VideoType.class ) );

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
            .andExpect( status().isConflict() );
    }

    /**
     * Test for find action.
     * Success
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testFindActionSuccess() throws Exception {

        when( this.videoTypeService.find( anyLong() ) ).thenReturn(
            VideoTypeMock.find()
        );

        this.mockMvc.perform(
            get( "/music/videos/types/{id}", 1 )
                .contentType( MediaType.APPLICATION_JSON )
        )
            .andExpect( status().isOk() );
    }

    /**
     * Test for find action.
     * Failure
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testFindActionfailure() throws Exception {

        when( this.videoTypeService.find( anyLong() ) ).thenReturn( null );

        this.mockMvc.perform(
            get( "/music/videos/types/{id}", 1 )
                .contentType( MediaType.APPLICATION_JSON )
        )
            .andExpect( status().isNotFound() );
    }

    /**
     * Test for update action.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testUpdateActionSuccess() throws Exception {

        when( this.videoTypeService.find( anyLong() ) ).thenReturn( VideoTypeMock.find() );
        when( this.videoTypeService.update( any( VideoType.class )) ).thenReturn(
            VideoTypeMock.find()
        );

        this.mockMvc.perform(
            put( "/music/videos/types/{id}", 1 )
                .contentType( MediaType.APPLICATION_JSON )
                .content(
                    "{" +
                        "\"code\": \"YOUTUBE\"," +

                        "\"title\": \"Youtube\"," +
                        "\"description\": \"Youtube!\"" +
                    "}"
                )
        )
            .andExpect( status().isOk() );
    }

    /**
     * Test for update action.
     * Failure
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testUpdateActionFailure() throws Exception {
        //- Mock -//
        when( this.videoTypeService.find( anyLong() ) ).thenReturn( VideoTypeMock.find() );
        doThrow( DataIntegrityViolationException.class ).when(
            this.videoTypeService
        ).update( any( VideoType.class ) );

        this.mockMvc.perform(
            put( "/music/videos/types/{id}", 1 )
                .contentType( MediaType.APPLICATION_JSON )
                .content(
                    "{" +
                        "\"code\": \"YOUTUBE\"," +
                        "\"title\": \"Youtube\"," +
                        "\"description\": \"Youtube.\"" +
                    "}"
                )
        )
            .andExpect( status().isConflict() );
    }



    /**
     * Test for delete action.
     * Success
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testDeleteActionSuccess() throws Exception {

        doNothing().when( this.videoTypeService ).delete( anyLong() );

        this.mockMvc.perform(
            delete( "/music/videos/types/{id}", 1 )
                .contentType( MediaType.APPLICATION_JSON )
        )
            .andExpect( status().isOk() );
    }

    /**
     * Test for delete action.
     * Failure
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testDeleteActionfailure() throws Exception {

        doThrow( EmptyResultDataAccessException.class ).when(
            this.videoTypeService
        ).delete( anyLong() );

        this.mockMvc.perform(
            delete( "/music/videos/types/{id}", 1 )
                .contentType( MediaType.APPLICATION_JSON )
        )
            .andExpect( status().isNotFound() );
    }
}

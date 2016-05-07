/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 5/7/16 6:09 PM
 */

package com.coffeine.virtuoso.music.controller;

import com.coffeine.virtuoso.module.controller.AbstractRestControllerTest;
import com.coffeine.virtuoso.music.model.entity.Video;
import com.coffeine.virtuoso.music.model.persistence.mock.SongMock;
import com.coffeine.virtuoso.music.model.persistence.mock.VideoMock;
import com.coffeine.virtuoso.music.model.persistence.mock.VideoTypeMock;
import com.coffeine.virtuoso.music.model.service.SongService;
import com.coffeine.virtuoso.music.model.service.VideoService;
import com.coffeine.virtuoso.music.model.service.VideoTypeService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
 * Tests for VideoController.
 *
 * @version 1.0
 */
public class VideoControllerTest extends AbstractRestControllerTest {

    @Mock
    private SongService songService;

    @Mock
    private VideoTypeService videoTypeService;

    @Mock
    private VideoService videoService;

    @InjectMocks
    private VideoController videoController;


    /**
     * Init environment for run test.
     */
    @Before
    @Override
    public void tearUp() {

        super.tearUp();

        //- Set up application -//
        this.mockMvc = MockMvcBuilders.standaloneSetup(
            this.videoController
        ).build();
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
     * Test get list of staffs.
     *
     * @throws Exception
     */
    @Test
    public void testListActionSuccess() throws Exception {
        //- Mock -//
        when( this.videoService.findAll( anyInt(), anyInt() ) ).thenReturn( VideoMock.findAll() );

        //- Success -//
        this.mockMvc.perform(
            get( "/music/videos?page={page}&limit={limit}", 1, 10 )
        )
            .andExpect( status().isOk() );
    }

    /**
     * Test get video.
     * Success.
     *
     * @throws Exception
     */
    @Test
    public void testFindActionSuccess() throws Exception {
        //- Mock -//
        when( this.videoService.find( anyLong() ) ).thenReturn( VideoMock.find() );

        //- Success -//
        this.mockMvc.perform(
            get( "/music/videos/{id}", 1 )
        )
            .andExpect( status().isOk() );
    }

    /**
     * Test get video.
     * Failure.
     *
     * @throws Exception
     */
    @Test
    public void testFindActionFailure() throws Exception {
        //- Mock -//
        when( this.videoService.find( anyLong() ) ).thenReturn( null );

        //- Failure -//
        this.mockMvc.perform(
            get( "/music/videos/{id}", 1 )
        )
            .andExpect( status().isNotFound() );
    }

    /**
     * Test create a video.
     * Success.
     *
     * @throws Exception
     */
    @Test
    public void testCreateActionSuccess() throws Exception {
        //- Mock -//
        when( this.songService.find( anyLong() ) ).thenReturn( SongMock.retrieve() );
        when( this.videoTypeService.find( anyLong() ) ).thenReturn( VideoTypeMock.find() );
        when( this.videoService.create( any( Video.class ) ) ).thenReturn( VideoMock.find() );

        //- Success -//
        this.mockMvc.perform(
            post( "/music/videos" )
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
            .andExpect( status().isCreated() );
    }

    /**
     * Test create a video.
     * Failure.
     *
     * @throws Exception
     */
    @Test
    public void testCreateActionFailure() throws Exception {
        //- Mock -//
        when( this.songService.find( anyLong() ) ).thenReturn( SongMock.retrieve() );
        when( this.videoTypeService.find( anyLong() ) ).thenReturn( VideoTypeMock.find() );
        doThrow( DataIntegrityViolationException.class ).when(
            this.videoService
        ).create( any( Video.class ) );

        //- Failure -//
        this.mockMvc.perform(
            post( "/music/videos" )
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
            .andExpect( status().isConflict() );
    }

    /**
     * Test update a video.
     * Success.
     *
     * @throws Exception
     */
    @Test
    public void testUpdateActionSuccess() throws Exception {
        //- Mock -//
        when( this.videoService.find( anyLong() ) ).thenReturn( VideoMock.find() );
        when( this.songService.find( anyLong() ) ).thenReturn( SongMock.retrieve() );
        when( this.videoTypeService.find( anyLong() ) ).thenReturn( VideoTypeMock.find() );
        when( this.videoService.update( any( Video.class ) ) ).thenReturn( VideoMock.find() );

        //- Success -//
        this.mockMvc.perform(
            put( "/music/videos/{id}", 1 )
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
            .andExpect( status().isOk() );
    }

    /**
     * Test update a staff.
     * Failure.
     *
     * @throws Exception
     */
    @Test
    public void testUpdateActionFailure() throws Exception {
        //- Mock -//
        when( this.videoService.find( anyLong() ) ).thenReturn( VideoMock.find() );
        when( this.songService.find( anyLong() ) ).thenReturn( SongMock.retrieve() );
        when( this.videoTypeService.find( anyLong() ) ).thenReturn( VideoTypeMock.find() );
        doThrow( DataIntegrityViolationException.class ).when(
            this.videoService
        ).update( any( Video.class ) );

        //- Success -//
        this.mockMvc.perform(
            put( "/music/videos/{id}", 1 )
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
            .andExpect( status().isConflict() );
    }

    /**
     * Test delete a staff.
     * Success.
     *
     * @throws Exception
     */
    @Test
    public void testDeleteActionSuccess() throws Exception {
        //- Mock -//
        doNothing().when( this.videoService ).delete( anyLong() );

        //- Success -//
        this.mockMvc.perform(
            delete( "/music/videos/{id}", 1 )
        )
            .andExpect( status().isOk() );
    }

    /**
     * Test delete a staff.
     * Failure.
     *
     * @throws Exception
     */
    @Test
    public void testDeleteActionFailure() throws Exception {
        //- Mock -//
        doThrow( EmptyResultDataAccessException.class ).when(
            this.videoService
        ).delete( anyLong() );

        //- Success -//
        this.mockMvc.perform(
            delete( "/music/staffs/{id}", 1 )
        )
            .andExpect( status().isNotFound() );
    }
}

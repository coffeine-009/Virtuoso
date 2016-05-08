/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 5/8/16 10:40 AM
 */

package com.coffeine.virtuoso.music.controller;

import com.coffeine.virtuoso.module.controller.AbstractRestControllerTest;
import com.coffeine.virtuoso.music.model.entity.Text;
import com.coffeine.virtuoso.music.model.persistence.mock.SongMock;
import com.coffeine.virtuoso.music.model.persistence.mock.TextMock;
import com.coffeine.virtuoso.music.model.service.SongService;
import com.coffeine.virtuoso.music.model.service.TextService;

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
 * Tests for TextController.
 *
 * @version 1.0
 */
public class TextControllerTest extends AbstractRestControllerTest {

    @Mock
    private SongService songService;

    @Mock
    private TextService textService;

    @InjectMocks
    private TextController textController;


    /**
     * Init environment for run test.
     */
    @Before
    @Override
    public void tearUp() {

        super.tearUp();

        //- Set up application -//
        this.mockMvc = MockMvcBuilders.standaloneSetup(
            this.textController
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
     * Test get list of texts.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testListActionSuccess() throws Exception {
        //- Mock -//
        when( this.textController.findAll( anyInt(), anyInt() ) ).thenReturn( TextMock.findAll() );

        //- Success -//
        this.mockMvc.perform(
            get( "/music/texts?page={page}&limit={limit}", 1, 10 )
        )
            .andExpect( status().isOk() );
    }

    /**
     * Test get text.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testFindActionSuccess() throws Exception {
        //- Mock -//
        when( this.textService.find( anyLong() ) ).thenReturn( TextMock.find() );

        //- Success -//
        this.mockMvc.perform(
            get( "/music/texts/{id}", 1 )
        )
            .andExpect( status().isOk() );
    }

    /**
     * Test get text.
     * Failure.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testFindActionFailure() throws Exception {
        //- Mock -//
        when( this.textService.find( anyLong() ) ).thenReturn( null );

        //- Failure -//
        this.mockMvc.perform(
            get( "/music/texts/{id}", 1 )
        )
            .andExpect( status().isNotFound() );
    }

    /**
     * Test create a text.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testCreateActionSuccess() throws Exception {
        //- Mock -//
        when( this.songService.find( anyLong() ) ).thenReturn( SongMock.retrieve() );
        when( this.textService.create( any( Text.class ) ) ).thenReturn( TextMock.find() );

        //- Success -//
        this.mockMvc.perform(
            post( "/music/texts" )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"songId\": 1," +
                        "\"locale\": \"uk-UA\"" +
                    "}"
                )
        )
            .andExpect( status().isCreated() );
    }

    /**
     * Test create a text.
     * Failure.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testCreateActionFailure() throws Exception {
        //- Conflict -//
        //- Mock -//
        when( this.songService.find( anyLong() ) ).thenReturn( SongMock.retrieve() );
        doThrow( DataIntegrityViolationException.class ).when(
            this.textService
        ).create( any( Text.class ) );

        //- Failure -//
        this.mockMvc.perform(
            post( "/music/texts" )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"songId\": 1," +
                        "\"locale\": \"uk-UA\"" +
                    "}"
                )
        )
            .andExpect( status().isConflict() );

        //- Not found -//
        //- Mock -//
        when( this.songService.find( anyLong() ) ).thenReturn( null );
        doThrow( DataIntegrityViolationException.class ).when(
            this.textService
        ).create( any( Text.class ) );

        //- Failure -//
        this.mockMvc.perform(
            post( "/music/texts" )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"songId\": 99999," +
                        "\"locale\": \"uk-UA\"" +
                    "}"
                )
        )
            .andExpect( status().isNotFound() );
    }

    /**
     * Test update a text.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testUpdateActionSuccess() throws Exception {
        //- Mock -//
        when( this.textService.find( anyLong() ) ).thenReturn( TextMock.find() );
        when( this.songService.find( anyLong() ) ).thenReturn( SongMock.retrieve() );
        when( this.textService.update( any( Text.class ) ) ).thenReturn( TextMock.find() );

        //- Success -//
        this.mockMvc.perform(
            put( "/music/texts/{id}", 1 )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"songId\": 1," +
                        "\"locale\": \"uk-UA\"" +
                    "}"
                )
        )
            .andExpect( status().isOk() );
    }

    /**
     * Test update a text.
     * Failure.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testUpdateActionFailure() throws Exception {
        //- Conflict -//
        //- Mock -//
        when( this.textService.find( anyLong() ) ).thenReturn( TextMock.find() );
        when( this.songService.find( anyLong() ) ).thenReturn( SongMock.retrieve() );
        doThrow( DataIntegrityViolationException.class ).when(
            this.textService
        ).update( any( Text.class ) );

        //- Success -//
        this.mockMvc.perform(
            put( "/music/texts/{id}", 1 )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"songId\": 1," +
                        "\"locale\": \"uk-UA\"" +
                    "}"
                )
        )
            .andExpect( status().isConflict() );

        //- Not found -//
        //- Mock -//
        when( this.textService.find( anyLong() ) ).thenReturn( null );
        when( this.songService.find( anyLong() ) ).thenReturn( null );
        doThrow( DataIntegrityViolationException.class ).when(
            this.textService
        ).update( any( Text.class ) );

        //- Success -//
        this.mockMvc.perform(
            put( "/music/texts/{id}", 1 )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"songId\": 99999," +
                        "\"locale\": \"uk-UA\"" +
                    "}"
                )
        )
            .andExpect( status().isNotFound() );
    }

    /**
     * Test delete a text.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testDeleteActionSuccess() throws Exception {
        //- Mock -//
        doNothing().when( this.textService ).delete( anyLong() );

        //- Success -//
        this.mockMvc.perform(
            delete( "/music/texts/{id}", 1 )
        )
            .andExpect( status().isOk() );
    }

    /**
     * Test delete a text.
     * Failure.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testDeleteActionFailure() throws Exception {
        //- Mock -//
        doThrow( EmptyResultDataAccessException.class ).when(
            this.textService
        ).delete( anyLong() );

        //- Success -//
        this.mockMvc.perform(
            delete( "/music/texts/{id}", 1 )
        )
            .andExpect( status().isNotFound() );
    }
}

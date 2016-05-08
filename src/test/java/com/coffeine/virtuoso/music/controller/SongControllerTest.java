/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 3/20/16 10:24 PM
 */

package com.coffeine.virtuoso.music.controller;

import com.coffeine.virtuoso.module.controller.AbstractRestControllerTest;
import com.coffeine.virtuoso.music.model.entity.Song;
import com.coffeine.virtuoso.music.model.persistence.mock.ComposerMock;
import com.coffeine.virtuoso.music.model.persistence.mock.PoetMock;
import com.coffeine.virtuoso.music.model.persistence.mock.SongMock;
import com.coffeine.virtuoso.music.model.service.ComposerService;
import com.coffeine.virtuoso.music.model.service.PoetService;
import com.coffeine.virtuoso.music.model.service.SongService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for SongController.
 *
 * @version 1.0
 * @see SongController
 */
public class SongControllerTest extends AbstractRestControllerTest {

    /// *** Properties  *** ///
    @Mock
    private ComposerService composerService;

    @Mock
    private PoetService poetService;

    @Mock
    private SongService songService;

    @InjectMocks
    private SongController songController;


    /// *** Methods     *** ///
    /**
     * Init environment for run test
     */
    @Before
    @Override
    public void tearUp() {

        super.tearUp();

        //- Set up application -//
        this.mockMvc = MockMvcBuilders.standaloneSetup( songController ).build();
    }

    /**
     * Reset environment to previous state
     */
    @After
    @Override
    public void tearDown() {
        //- Clean environment after run tests -//
    }


    /**
     * Test getting list of songs
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testListAction() throws Exception {

        //- Mock service -//
        when( songService.findAll( anyInt(), anyInt() ) ).thenReturn( SongMock.getList() );

        // Success. Get list of songs
        this.mockMvc.perform(
            get( "/music/songs?page={page}&limit={limit}", 1, 10 )
        )
            .andExpect( status().isOk() );
    }

    /**
     * Test getting list of songs in failure case.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testListFailureAction() throws Exception {

        //- Mock service -//
        when( songService.findAll( anyInt(), anyInt() ) ).thenReturn( new ArrayList<>( 0 ) );

        // Success. Get list of songs
        this.mockMvc.perform(
            get( "/music/songs?page={page}&limit={limit}", 1, 10 )
        )
            .andExpect( status().isOk() )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andExpect( jsonPath( "$", hasSize( 0 ) ) );
    }

    /**
     * Test create new song.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testCreateActionSuccess() throws Exception {
        //- Mock -//
        when( this.composerService.find( anyListOf( Long.class ) ) ).thenReturn( ComposerMock.findAll() );
        when( this.poetService.find( anyListOf( Long.class ) ) ).thenReturn( PoetMock.findAll() );
        when( this.songService.create( any( Song.class ) ) ).thenReturn( SongMock.retrieve() );

        //- Success. Create a new songs -//
        this.mockMvc.perform(
            post( "/music/songs" )
                .header( "Authorization", this.session.getAuthorizationHeader() )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"composerIds\": [ 1 ]," +
                        "\"poetIds\": [ 1 ]," +
                        "\"data\": [ {" +
                            "\"title\": \"Rose\"," +
                            "\"locale\": \"en-US\"" +
                        "} ]," +
                        "\"staffs\": [ {" +
                            "\"musicNotesTypeId\": 1," +
                            "\"styleId\": 1" +
                        "} ]," +
                        "\"texts\": [ {" +
                            "\"locale\": \"en-US\"," +
                            "\"lyrics\": \"Слова, слова,  \\n" +
                                "Немов вуаль,  \\n" +
                                "Там, де тонка діагональ  \\n" +
                                "Звучить кришталь.  \\n" +
                                "А ніч летить туди у даль,  \\n" +
                                "Не залишаючи для нас  \\n" +
                                "Путів, на жаль...  \\n" +
                                "\\n" +
                                "#### Приспів:  \\n" +
                                "Стріляй!  \\n" +
                                "Скажи, чому боїшся ти  \\n" +
                                "Зробити цей останній крок?!?  \\n" +
                                "Давай!  \\n" +
                                "Най буде так, як хочеш ти,  \\n" +
                                "Я заплатив за свій урок!  \\n" +
                                "Прощай, мій Ангелок...  \\n" +
                                "Давай! Тисни гачок!  \\n" +
                                "\\n" +
                                "Слова, слова - \\n" +
                                "Алмаз вини -  \\n" +
                                "Мов зачаїлися в думках  \\n" +
                                "На мить вони...  \\n" +
                                "І от дуель, і от фінал  \\n" +
                                "Там, де навколо Колізей  \\n" +
                                "Звучить метал...  \\n" +
                                "\\n" +
                                "#### Приспів. (2)\\n" +
                                "\\n" +
                                ">Прощай, мій Ангелок...  \\n" +
                                "Стріляй!\"" +
                        "} ]," +
                        "\"videos\": [ {" +
                            "\"locale\": \"en-US\"," +
                            "\"title\": \"Rose\"," +
                            "\"description\": \"Rose.\"," +
                            "\"link\": \"rose.mp4\"" +
                        "} ]," +
                        "\"locale\": \"en-US\"," +
                        "\"writeDate\": \"2016-05-08\"" +
                    "}"
                )
        )
            .andExpect( status().isCreated() );
    }

    /**
     * Test create new song.
     * Failure
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testCreateActionFailure() throws Exception {
        //- Params -//
        final String content = "{" +
            "\"composerIds\": [ 1 ]," +
            "\"poetIds\": [ 1 ]," +
            "\"data\": [ {" +
                "\"title\": \"Rose\"," +
                "\"locale\": \"en-US\"" +
            "} ]," +
            "\"staffs\": [ {" +
                "\"musicNotesTypeId\": 1," +
                "\"styleId\": 1" +
            "} ]," +
            "\"texts\": [ {" +
                "\"locale\": \"en-US\"," +
                "\"lyrics\": \"Слова, слова,  \\n" +
                    "Немов вуаль,  \\n" +
                    "Там, де тонка діагональ  \\n" +
                    "Звучить кришталь.  \\n" +
                    "А ніч летить туди у даль,  \\n" +
                    "Не залишаючи для нас  \\n" +
                    "Путів, на жаль...\"" +
            "} ]," +
            "\"videos\": [ {" +
                "\"locale\": \"en-US\"," +
                "\"title\": \"Rose\"," +
                "\"description\": \"Rose.\"," +
                "\"link\": \"rose.mp4\"" +
            "} ]," +
            "\"locale\": \"en-US\"," +
            "\"writeDate\": \"2016-05-08\"" +
        "}";

        //- Conflict -//
        //- Mock -//
        when( this.composerService.find( anyListOf( Long.class ) ) ).thenReturn( ComposerMock.findAll() );
        when( this.poetService.find( anyListOf( Long.class ) ) ).thenReturn( PoetMock.findAll() );
        doThrow( DataIntegrityViolationException.class ).when(
            this.songService
        ).create( any( Song.class ) );

        //- Failure. Create a new songs -//
        this.mockMvc.perform(
            post( "/music/songs" )
                .header( "Authorization", this.session.getAuthorizationHeader() )
                .header( "Content-Type", "application/json" )
                .content( content )
        )
            .andExpect( status().isConflict() );

        //- Not Found -//
        //- Mock -//
        when( this.composerService.find( anyListOf( Long.class ) ) ).thenReturn( null );
        when( this.poetService.find( anyListOf( Long.class ) ) ).thenReturn( null );
        doThrow( DataIntegrityViolationException.class ).when(
            this.songService
        ).create( any( Song.class ) );

        //- Failure. Create a new songs -//
        this.mockMvc.perform(
            post( "/music/songs" )
                .header( "Authorization", this.session.getAuthorizationHeader() )
                .header( "Content-Type", "application/json" )
                .content( content )
        )
            .andExpect( status().isNotFound() );
    }

    /**
     * Test of retrieving song by id.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testRetrieveActionSuccess() throws Exception {
        //- Mock service -//
        when( songService.find( anyLong() ) ).thenReturn( SongMock.retrieve() );

        // Success. Get list of songs
        this.mockMvc.perform(
            get( "/music/songs/{id}", 1 )
        )
            .andExpect( status().isOk() );
    }

    /**
     * Test of retrieving song by id.
     * Failure.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testRetrieveActionFailure() throws Exception {
        //- Mock service -//
        when( songService.find( anyLong() ) ).thenReturn( null );

        // Success. Get list of songs
        this.mockMvc.perform(
            get( "/music/songs/{id}", 1 )
        )
            .andExpect( status().isNotFound() );
    }

    /**
     * Test update song.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testUpdateActionSuccess() throws Exception {
        //- Mock -//
        when( this.composerService.find( anyListOf( Long.class ) ) ).thenReturn( ComposerMock.findAll() );
        when( this.poetService.find( anyListOf( Long.class ) ) ).thenReturn( PoetMock.findAll() );
        when( this.songService.find( anyLong() ) ).thenReturn( SongMock.retrieve() );
        when( this.songService.update( any( Song.class ) ) ).thenReturn( SongMock.retrieve() );

        //- Success. Create a new songs -//
        this.mockMvc.perform(
            put( "/music/songs/{id}", 1 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
                .header( "Content-Type", "application/json" )
                .content(
                    "{" +
                        "\"composerIds\": [ 1 ]," +
                        "\"poetIds\": [ 1 ]," +
                        "\"data\": [ {" +
                            "\"title\": \"Rose\"," +
                            "\"locale\": \"en-US\"" +
                        "} ]," +
                        "\"staffs\": [ {" +
                            "\"musicNotesTypeId\": 1," +
                            "\"styleId\": 1" +
                        "} ]," +
                        "\"texts\": [ {" +
                            "\"locale\": \"en-US\"," +
                            "\"lyrics\": \"Слова, слова,  \\n" +
                                "Немов вуаль,  \\n" +
                                "Там, де тонка діагональ  \\n" +
                                "Звучить кришталь.  \\n" +
                                "А ніч летить туди у даль,  \\n" +
                                "Не залишаючи для нас  \\n" +
                                "Путів, на жаль...  \\n" +
                                "\\n" +
                                "#### Приспів:  \\n" +
                                "Стріляй!  \\n" +
                                "Скажи, чому боїшся ти  \\n" +
                                "Зробити цей останній крок?!?  \\n" +
                                "Давай!  \\n" +
                                "Най буде так, як хочеш ти,  \\n" +
                                "Я заплатив за свій урок!  \\n" +
                                "Прощай, мій Ангелок...  \\n" +
                                "Давай! Тисни гачок!  \\n" +
                                "\\n" +
                                "Слова, слова - \\n" +
                                "Алмаз вини -  \\n" +
                                "Мов зачаїлися в думках  \\n" +
                                "На мить вони...  \\n" +
                                "І от дуель, і от фінал  \\n" +
                                "Там, де навколо Колізей  \\n" +
                                "Звучить метал...  \\n" +
                                "\\n" +
                                "#### Приспів. (2)\\n" +
                                "\\n" +
                                ">Прощай, мій Ангелок...  \\n" +
                                "Стріляй!\"" +
                        "} ]," +
                        "\"videos\": [ {" +
                            "\"locale\": \"en-US\"," +
                            "\"title\": \"Rose\"," +
                            "\"description\": \"Rose.\"," +
                            "\"link\": \"rose.mp4\"" +
                        "} ]," +
                        "\"locale\": \"en-US\"," +
                        "\"writeDate\": \"2016-05-08\"" +
                    "}"
                )
        )
            .andExpect( status().isOk() );
    }

    /**
     * Test update song.
     * Failure.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testUpdateActionFailure() throws Exception {
        //- Params -//
        final String content = "{" +
            "\"composerIds\": [ 1 ]," +
            "\"poetIds\": [ 1 ]," +
            "\"data\": [ {" +
                "\"title\": \"Rose\"," +
                "\"locale\": \"en-US\"" +
            "} ]," +
            "\"staffs\": [ {" +
                "\"musicNotesTypeId\": 1," +
                "\"styleId\": 1" +
            "} ]," +
            "\"texts\": [ {" +
                "\"locale\": \"en-US\"," +
                "\"lyrics\": \"Слова, слова,  \\n" +
                    "Немов вуаль,  \\n" +
                    "Там, де тонка діагональ  \\n" +
                    "Звучить кришталь.  \\n" +
                    "А ніч летить туди у даль,  \\n" +
                    "Не залишаючи для нас  \\n" +
                    "Путів, на жаль...\"" +
            "} ]," +
            "\"videos\": [ {" +
                "\"locale\": \"en-US\"," +
                "\"title\": \"Rose\"," +
                "\"description\": \"Rose.\"," +
                "\"link\": \"rose.mp4\"" +
            "} ]," +
            "\"locale\": \"en-US\"," +
            "\"writeDate\": \"2016-05-08\"" +
        "}";

        //- Conflict -//
        //- Mock -//
        when( this.composerService.find( anyListOf( Long.class ) ) ).thenReturn( ComposerMock.findAll() );
        when( this.poetService.find( anyListOf( Long.class ) ) ).thenReturn( PoetMock.findAll() );
        when( this.songService.find( anyLong() ) ).thenReturn( SongMock.retrieve() );
        doThrow( DataIntegrityViolationException.class ).when(
            this.songService
        ).update( any( Song.class ) );

        //- Failure -//
        this.mockMvc.perform(
            put( "/music/songs/{id}", 1 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
                .header( "Content-Type", "application/json" )
                .content( content )
        )
            .andExpect( status().isConflict() );

        //- Not Found -//
        //- Mock -//
        when( this.composerService.find( anyListOf( Long.class ) ) ).thenReturn( null );
        when( this.poetService.find( anyListOf( Long.class ) ) ).thenReturn( null );
        when( this.songService.find( anyLong() ) ).thenReturn( SongMock.retrieve() );
        doThrow( DataIntegrityViolationException.class ).when(
            this.songService
        ).create( any( Song.class ) );

        //- Failure -//
        this.mockMvc.perform(
            put( "/music/songs/{id}", 99999 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
                .header( "Content-Type", "application/json" )
                .content( content )
        )
            .andExpect( status().isNotFound() );
    }

    /**
     * Test of deleting song.
     * Success
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testDeleteActionSuccess() throws Exception {
        //- Mock -//
        doNothing().when( this.songService ).delete( anyLong() );

        //- Success -//
        this.mockMvc.perform(
            delete( "/music/songs/{id}", 1 )
        )
            .andExpect( status().isOk() );
    }

    /**
     * Test of deleting song.
     * Failure.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testDeleteActionFailure() throws Exception {
        //- Mock -//
        doThrow( EmptyResultDataAccessException.class ).when(
            this.songService
        ).delete( anyLong() );

        //- Failure -//
        this.mockMvc.perform(
            delete( "/music/songs/{id}", 1 )
        )
            .andExpect( status().isNotFound() );
    }
}

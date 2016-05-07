/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 3/20/16 10:24 PM
 */

package com.coffeine.virtuoso.music.controller;

import com.coffeine.virtuoso.module.controller.AbstractRestControllerTest;
import com.coffeine.virtuoso.music.model.persistence.mock.SongMock;
import com.coffeine.virtuoso.music.model.service.SongService;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
            .andExpect( status().isOk() )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andExpect( jsonPath( "$", hasSize( 1 ) ) )
            .andExpect( jsonPath( "$[*].id", notNullValue() ) )
            .andExpect( jsonPath( "$[*].id", containsInAnyOrder( 1 ) ) )
            //- Composer -//
            .andExpect( jsonPath( "$[*].composers", notNullValue() ) )
            .andExpect( jsonPath( "$[*].composers", not( empty() ) ) )
            .andExpect( jsonPath( "$[*].composers", hasSize( 1 ) ) )
            .andExpect( jsonPath( "$[*].composers[*].id", containsInAnyOrder( 1 ) ) )
            .andExpect( jsonPath( "$[*].composers[*].data[*].firstName", containsInAnyOrder( "Test" ) ) )
            .andExpect( jsonPath( "$[*].composers[*].data[*].lastName", containsInAnyOrder( "Unit" ) ) )
            .andExpect( jsonPath( "$[*].composers[*].data[*].middleName", containsInAnyOrder( "Mockito" ) ) )
            .andExpect( jsonPath( "$[*].composers[*].data[*].locale", containsInAnyOrder( "uk-UA" ) ) )
            .andExpect( jsonPath( "$[*].composers[*].locale", containsInAnyOrder( "uk-UA" ) ) )
            .andExpect( jsonPath( "$[*].composers[*].gender", containsInAnyOrder( true ) ) )
            .andExpect( jsonPath( "$[*].composers[*].birthday", notNullValue() ) )
            .andExpect( jsonPath( "$[*].composers[0].deathDate", nullValue() ) )
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
            //- Song -//
            .andExpect( jsonPath( "$[*].title", notNullValue() ) )
            .andExpect( jsonPath( "$[*].locale", notNullValue() ) )
            .andExpect( jsonPath( "$[*].writeDate", notNullValue() ) )
            .andExpect( jsonPath( "$[*].data", notNullValue() ) )
            .andExpect( jsonPath( "$[*].staffs", notNullValue() ) )
            .andExpect( jsonPath( "$[*].texts", notNullValue() ) )
            .andExpect( jsonPath( "$[*].videos", notNullValue() ) );
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
     * Test create new song
     *
     * @throws Exception    General Exception of application.
     */
    @Ignore
    @Test
    public void testCreateAction() throws Exception {
        // Do request for get list of songs
//        HttpResponse songsResponse = HttpClientBuilder.create().build().execute(
//            new HttpPost(
//                String.format(
//                    "%s://%s:%s%s",
//                    //- Params -//
//                    PROTOCOL,
//                    DOMAIN,
//                    PORT,
//                    URL_SONG_CREATE
//                )
//            )
//        );
//        String body = EntityUtils.toString(
//                songsResponse.getEntity()
//        );
//        this.fail("To implement");
    }

    /**
     * Test of retrieving song by id.
     * Success.
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testRetrieveAction() throws Exception {
        //- Mock service -//
        when( songService.find( anyLong() ) ).thenReturn( SongMock.retrieve() );

        // Success. Get list of songs
        this.mockMvc.perform(
            get( "/music/songs/{id}", 1 )
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
            //- Song -//
//            .andExpect( jsonPath( "$title", notNullValue() ) )//FIXME
            .andExpect( jsonPath( "$locale", notNullValue() ) )
//            .andExpect( jsonPath( "$writeDate", notNullValue() ) )//FIXME
            .andExpect( jsonPath( "$data", notNullValue() ) )
            .andExpect( jsonPath( "$staffs", notNullValue() ) )
            .andExpect( jsonPath( "$texts", notNullValue() ) )
            .andExpect( jsonPath( "$videos", notNullValue() ) );
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

    @Ignore
    @Test
    public void testUpdateAction() throws Exception {
//        this.fail("To implement");
    }

    @Test
    public void testDeleteAction() throws Exception {

        this.mockMvc.perform(
            delete( "/music/songs/{SONG_ID}", 1 )
                .contentType( MediaType.APPLICATION_JSON )
        )
            .andDo( print() );
    }
}

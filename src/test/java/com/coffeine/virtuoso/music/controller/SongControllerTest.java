/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
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

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for SongController.
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
    public void tearDown() {

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
            .andExpect(status().isOk() )
            .andDo( print() );
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

    @Ignore
    @Test
    public void testReadAction() throws Exception {
        //this.fail("To implement");
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

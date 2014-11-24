/// *** User :: Controller :: Song  *** *** *** *** *** *** *** *** *** *** ///

    /** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *
     *                                                                  *
     * @copyright 2014 (c), by Coffeine
     *
     * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
     *
     * @date 2014-05-03 16:34:32 :: ....-..-.. ..:..:..
     *
     * @address /Ukraine/Ivano-Frankivsk/Tychyny/7a
     *                                                                  *
    *///*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.module.user.controller;

import com.coffeine.virtuoso.module.controller.AbstractRestControllerTest;
import com.coffeine.virtuoso.module.user.model.persistence.mock.SongMock;
import com.coffeine.virtuoso.module.user.model.service.SongService;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for SongController
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

        //- Init mocks -//
        MockitoAnnotations.initMocks(this);

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
     * @throws Exception
     */
    @Test
    public void testListAction() throws Exception {

        //- Mock service -//
        when( songService.findAll( anyInt(), anyInt() ) ).thenReturn( SongMock.getList() );

        // Success. Get list of songs
        this.mockMvc.perform(
            get( "/user/song/list/{page}/{limit}", 1, 10 )
        )
            .andExpect(status().isOk() )
            .andDo( print() );
    }

    /**
     * Test create new song
     *
     * @throws Exception
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

    @Ignore
    @Test
    public void testDeleteAction() throws Exception {

        this.mockMvc.perform(
            delete( "/user/song/{SONG_ID}", 1 )
                .contentType( MediaType.APPLICATION_JSON )
        )
            .andDo( print() );
    }
}

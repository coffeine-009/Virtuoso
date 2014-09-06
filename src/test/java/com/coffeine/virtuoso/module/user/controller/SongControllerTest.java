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

import com.coffeine.virtuoso.module.controller.AbstractControllerTest;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for SongController
 */
public class SongControllerTest extends AbstractControllerTest {

    /// *** Constants   *** ///
    final String PROTOCOL   = "http";
    final String DOMAIN     = "localhost";
    final String PORT       = "8080";

    final String URL_SONG_LIST      = "/user/song/list";
    final String URL_SONG_CREATE    = "/user/song";

    /**
     * Init environment for run test
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {


    }

    /**
     * Reset environment to previous state
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {

    }

    /**
     * Test getting list of songs
     *
     * @throws Exception
     */
    @Test
    public void testListAction() throws Exception {
        // Do request for get list of songs
        this.mockMvc.perform(
            get( "/user/song/list" )
        )
            .andExpect( status().isUnauthorized() );
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
//        this.fail("To implement");
    }
}

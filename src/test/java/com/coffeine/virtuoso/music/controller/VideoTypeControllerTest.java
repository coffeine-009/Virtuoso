/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.controller;

import com.coffeine.virtuoso.module.controller.AbstractRestControllerTest;
import com.coffeine.virtuoso.music.model.service.VideoTypeService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Tests for VideoType Controller
 * @see VideoTypeController
 *
 * @version 1.0
 */
public class VideoTypeControllerTest extends AbstractRestControllerTest {

    /// *** Constants   *** ///
    protected final String URI_LIST = "/user/video/type/list/{PAGE}/{LIMIT}";

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

    //- SECTION :: TEST -//
    /**
     * Test for findAll action
     *
     * @throws Exception    General Exception of application.
     */
    @Test
    public void testFindAll() throws Exception {

        when( this.videoTypeService.findAll( anyInt(), anyInt() ) ).thenReturn( null );

        this.mockMvc.perform(
            get( URI_LIST, "1", "2" )
                .contentType( MediaType.APPLICATION_JSON )
//                .session( this.session )
        ).andDo( print() );
//            .andExpect( status().isOk() );
    }
}

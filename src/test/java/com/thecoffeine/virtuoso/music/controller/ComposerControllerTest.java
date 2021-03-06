/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

package com.thecoffeine.virtuoso.music.controller;

import com.thecoffeine.virtuoso.module.controller.AbstractRestControllerTest;
import com.thecoffeine.virtuoso.music.model.persistence.mock.ComposerMock;
import com.thecoffeine.virtuoso.music.model.service.ComposerService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for ComposerController.
 * @see ComposerController
 *
 * @version 1.0
 */
public class ComposerControllerTest extends AbstractRestControllerTest {

    /// *** Constants   *** ///
    private static final String RESOURCE_URI = "/music/composers";


    /// *** Properties  *** ///
    @Mock
    private ComposerService composerService;

    @InjectMocks
    private ComposerController composerController;


    /// *** Methods     *** ///
    /**
     * Init environment for run test.
     */
    @Before
    @Override
    public void tearUp() {

        super.tearUp();

        //- Set up application -//
        this.mockMvc = MockMvcBuilders
            .standaloneSetup( this.composerController )
            .build();
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
     * Test listAction.
     * @see ComposerController#listAction(int, int)
     *
     * @throws Exception General Exception of application.
     */
    @Test
    public void testListAction() throws Exception {
        //- Assumptions -//
        when(
            this.composerService.findAll( anyInt(), anyInt() )
        ).thenReturn( ComposerMock.findAll() );

        //- Execution -//
        // Success. Get list of songs
        this.mockMvc.perform(
            get( RESOURCE_URI )
                .requestAttr( "page", 1 )
                .requestAttr( "limit", 10 )
        )
            .andExpect( status().isOk() )
            .andExpect( jsonPath( "$", notNullValue() ) )
            .andDo( print() );//TODO

        //- Assertions -//
        verify( this.composerService, times( 1 ) ).findAll( 0, 10 );
    }
}

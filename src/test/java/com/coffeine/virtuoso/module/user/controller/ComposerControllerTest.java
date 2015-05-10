/*
 * @copyright (c) 2015 by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 */

package com.coffeine.virtuoso.module.user.controller;

import com.coffeine.virtuoso.module.controller.AbstractRestControllerTest;
import com.coffeine.virtuoso.module.user.model.persistence.mock.ComposeMock;
import com.coffeine.virtuoso.module.user.model.service.ComposerService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for ComposerController.
 * @see com.coffeine.virtuoso.module.user.controller.ComposerController
 *
 * @version 1.0
 */
public class ComposerControllerTest extends AbstractRestControllerTest {

    /// *** Constants   *** ///
    private static final String RESOURCE_URI = "/composers";


    /// *** Properties  *** ///
    @Mock
    private ComposerService composerService;

    @InjectMocks
    private ComposerController composerController;


    /// *** Methods     *** ///
    /**
     * Init environment for run test
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
     * Reset environment to previous state
     */
    @After
    public void tearDown() {

    }


    /**
     * Test listAction.
     * @see com.coffeine.virtuoso.module.user.controller.ComposerController#listAction(int, int)
     *
     * @throws Exception
     */
    @Test
    public void testListAction() throws Exception {
        //- Assumptions -//
        when(
            this.composerService.findAll( anyInt(), anyInt() )
        ).thenReturn( ComposeMock.findAll() );

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

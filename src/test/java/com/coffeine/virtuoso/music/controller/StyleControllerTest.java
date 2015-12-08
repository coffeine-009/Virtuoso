/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:47 PM
 */

package com.coffeine.virtuoso.music.controller;

import com.coffeine.virtuoso.module.controller.AbstractRestControllerTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Unit tests for StyleController.
 * @see StyleController
 */
public class StyleControllerTest extends AbstractRestControllerTest {

    @InjectMocks
    private StyleController styleController;

    /**
     * Init environment for run test
     */
    @Before
    @Override
    public void tearUp() {

        super.tearUp();

        //- Set up application -//
        this.mockMvc = MockMvcBuilders.standaloneSetup( styleController ).build();
    }

    //TODO: put here tests
    @Test
    public void testFilterActionSuccess() throws Exception {

    }
}

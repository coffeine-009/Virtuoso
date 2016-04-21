/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/8/15 10:11 PM
 */

package com.coffeine.virtuoso.music.controller;

import com.coffeine.virtuoso.module.controller.AbstractRestControllerTest;

import org.junit.Before;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Tests for StaffController.
 *
 * @version 1.0
 */
public class FunctionalStaffControllerTest extends AbstractRestControllerTest {

    /**
     * Prepare environment to run tests.
     */
    @Before
    @Override
    public void tearUp() {
        super.tearUp();
    }

    @Test
    public void testFindAllAction() throws Exception {
        // Success. Get list of songs
        this.mockMvc.perform(
            get( "/music/staffs/{id}", 1 )
                .header( "Authorization", this.session.getAuthorizationHeader() )
        ).andDo(print());
    }
}

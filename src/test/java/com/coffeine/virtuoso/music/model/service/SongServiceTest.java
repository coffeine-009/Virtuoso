/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.model.service;

import com.coffeine.virtuoso.module.model.service.AbstractServiceTest;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

/**
 * Tests for SongService
 * @see SongService
 *
 * @version 1.0
 */
public class SongServiceTest extends AbstractServiceTest {

    /// *** Properties  *** ///


    /// *** Methods     *** ///
    /**
     * Prepare environment for run tests
     */
    @Before
    @Override
    public void tearUp() {
        super.tearUp();

        //- Init mocks -//
        MockitoAnnotations.initMocks( this );
    }

    /**
     * Test findAll
     */
    @Ignore
    @Test
    public void testFindAll() {

    }
}

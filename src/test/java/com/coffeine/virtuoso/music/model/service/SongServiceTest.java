/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.model.service;

import com.coffeine.virtuoso.module.model.service.AbstractServiceTest;
import com.coffeine.virtuoso.music.model.entity.Song;
import com.coffeine.virtuoso.music.model.persistence.mock.SongMock;
import com.coffeine.virtuoso.music.model.repository.SongRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * Tests for SongService
 * @see SongService
 *
 * @version 1.0
 */
public class SongServiceTest extends AbstractServiceTest {

    /// *** Properties  *** ///
    @Mock
    private SongRepository songRepository;

    @InjectMocks
    @Autowired
    private SongService songService;


    /// *** Methods     *** ///
    /**
     * Prepare environment for run tests
     */
    @Before
    @Override
    public void tearUp() {
        //- Init mocks -//
        MockitoAnnotations.initMocks( this );
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
     * Test find.
     */
    @Test
    public void testFind() {

        when( this.songRepository.findOne( anyLong() ) ).thenReturn( SongMock.retrieve() );

        final Song song = this.songService.find( 1L );

        assertNotNull( song );
    }
}

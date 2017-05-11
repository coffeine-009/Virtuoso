/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

package com.thecoffeine.virtuoso.music.model.service;

import com.thecoffeine.virtuoso.music.model.entity.Song;
import com.thecoffeine.virtuoso.music.model.persistence.mock.SongMock;
import com.thecoffeine.virtuoso.music.model.repository.SongRepository;
import com.thecoffeine.virtuoso.music.model.service.implementation.SongServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * Tests for SongService
 * @see SongService
 *
 * @version 1.0
 */
@RunWith( MockitoJUnitRunner.class )
public class SongServiceTest {

    /// *** Properties  *** ///
    @Mock
    private SongRepository songRepository;

    @InjectMocks
    private SongService songService = new SongServiceImpl();


    /// *** Methods     *** ///
    /**
     * Prepare environment for run tests
     */
    @Before
    public void tearUp() {
        //- Init mocks -//
        MockitoAnnotations.initMocks( this );
    }

    /**
     * Clean environment.
     */
    @After
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

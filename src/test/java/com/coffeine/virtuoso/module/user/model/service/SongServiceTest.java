/**
 * @copyright (c) 2014, by Vitaliy Tsutsman
 *
 * @author Vitaliy Tsutsman <vtsutsman@softjourn.com>
 */

package com.coffeine.virtuoso.module.user.model.service;

import com.coffeine.virtuoso.module.model.service.AbstractServiceTest;
import com.coffeine.virtuoso.module.user.model.entity.Song;
import com.coffeine.virtuoso.module.user.model.repository.SongRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * Tests for SongService
 * @see com.coffeine.virtuoso.module.user.model.service.SongService
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
        super.tearUp();

        //- Init mocks -//
        MockitoAnnotations.initMocks( this );
    }

    /**
     * Test findAll
     */
    @Test
    public void testFindAll() {

        //- Make mock for service -//
        when(
            songRepository.findAll(
                new PageRequest(
                    anyInt(),
                    10
                )
            )
        ).thenReturn(
            new PageImpl < Song > (
                new ArrayList<Song>() {{
                    add( new Song() );
                }}
            )
        );

        //- Using of service -//
        List < Song > songs = this.songService.findAll( 0, 10 );

        //- Assertions -//
        assertEquals( 1, songs.size() );
    }
}

/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.model.persistence.mock;

import com.coffeine.virtuoso.music.model.entity.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock for song table in persistence layout.
 *
 * @version 1.0
 */
public abstract class SongMock {

    /**
     * Get list of songs.
     *
     * @return List of Songs.
     */
    public static List<Song> getList() {
        //- Create mock for song -//
        final Song song = new Song(
            ComposerMock.findAll(),
            PoetMock.findAll(),
            "uk-UA"
        );
        song.setId( 1L );

        return new ArrayList<Song>() {{
            add( song );
        }};
    }

    /**
     * Get song.
     *
     * @return Song.
     */
    public static Song retrieve() {
        Song song = new Song(
            ComposerMock.findAll(),
            PoetMock.findAll(),
            "uk-UA"
        );
        song.setId( 1L );

        return song;
    }
}

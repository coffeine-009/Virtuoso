/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.model.persistence.mock;

import com.coffeine.virtuoso.music.model.entity.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock for song table in persistence layout
 *
 * @version 1.0
 */
public class SongMock {

    /**
     * Get list of songs
     *
     * @return List of Songs.
     */
    public static List<Song> getList() {
        return new ArrayList < Song >() {{
            add(
                new Song()
            );
        }};
    }
}

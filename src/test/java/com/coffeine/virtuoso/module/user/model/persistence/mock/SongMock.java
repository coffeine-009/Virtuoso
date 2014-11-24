/**
 * @copyright (c) 2014, by Coffeine
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 */

package com.coffeine.virtuoso.module.user.model.persistence.mock;

import com.coffeine.virtuoso.module.user.model.entity.Song;

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
     * @return List<Song>
     */
    public static List < Song > getList() {
        return new ArrayList < Song >() {{
            add(
                new Song()
            );
        }};
    }
}

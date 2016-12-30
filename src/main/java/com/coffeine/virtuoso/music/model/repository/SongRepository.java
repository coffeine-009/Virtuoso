/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.model.repository;

import com.coffeine.virtuoso.music.model.entity.Song;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

/**
 * Interface for work with persistence layout.
 *
 * @version 1.0
 */
public interface SongRepository extends JpaRepository<Song, Long> {

    /**
     * Find songs by their ids.
     *
     * @param songsIds List of ids.
     *
     * @return List of songs.
     */
    @Query( "SELECT s FROM Song s WHERE s.id IN (:ids)" )
    List<Song> findByIds( @Param( "ids" ) Set<Long> songsIds );
}

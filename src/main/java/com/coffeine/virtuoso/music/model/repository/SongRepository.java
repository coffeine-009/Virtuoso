/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.model.repository;

import com.coffeine.virtuoso.music.model.entity.Song;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for work with persistence layout.
 *
 * @version 1.0
 */
public interface SongRepository extends JpaRepository < Song, Long > {

    /// *** Methods     *** ///
    /**
     * Find song by ID in persistence layout
     *
     * @return Song
     */
    //public Song findById( Long Id );
}
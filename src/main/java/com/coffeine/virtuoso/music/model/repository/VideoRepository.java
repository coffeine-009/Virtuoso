/**
 * Copyright (c) 2014-2016 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 12/7/15 10:23 PM
 */

package com.coffeine.virtuoso.music.model.repository;

import com.coffeine.virtuoso.music.model.entity.Video;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for work with persistence layout.
 *
 * @version 1.0
 */
public interface VideoRepository extends JpaRepository<Video, Long> {

    /// *** Methods     *** ///
}

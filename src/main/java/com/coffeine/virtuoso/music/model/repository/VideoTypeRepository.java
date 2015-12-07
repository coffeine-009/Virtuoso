/**
 * Copyright (c) 2014-2015 by Coffeine Inc
 *
 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
 *
 * @date 12/7/15 10:23 PM
 */

/// *** User :: Model :: Repository :: VideoType    *** *** *** *** *** *** ///

    //*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** *

/// *** Code    *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ///
package com.coffeine.virtuoso.music.model.repository;

import com.coffeine.virtuoso.music.model.entity.VideoType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface for work with persistence layout
 *
 * @version 1.0
 */
@Repository
public interface VideoTypeRepository extends JpaRepository < VideoType, Long > {

    /// *** Methods     *** ///
}
